package com.redsun.jira.util;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.api.domain.input.TransitionInput;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

/**
 * Created by xugr on 2017/4/6.
 */
public class JiraUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
//            JiraUtil.getIssue("NQCP-35");
//            JiraUtil.createIssue("NQCP", 1l, "工单详细信息<br>工单详细信息", "工单主题", "username");
//            JiraUtil.printAllIssueType();
//
//            JiraUtil.changeIssueStatus("NQCP-35", 2);
//            JiraUtil.getUser("username");

            //查询用户负责的所有工单
            JiraUtil.searchIssues("assignee=username");
            System.out.println("*****************************");
            JiraUtil.searchIssues("assignee=username order by duedate");
            System.out.println("*****************************");
            JiraUtil.searchIssues("assignee=username order by issueType");
            System.out.println("*****************************");
//            //从0开始数，从第5条开始，取两条
//            JiraUtil.searchIssues("assignee=username", 5, 2);
//
//            System.out.println("*****************************");
//            JiraUtil.searchIssues("project=NQCP");
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
    static String uri = "http://localhost:7777/jira";
    static String user = "admin";
    static String pwd = "admin";
    static JiraRestClient restClient;

    /**
     * 获得jira的客户端
     *
     * @return JiraRestClient
     * @throws URISyntaxException
     */
    static JiraRestClient getJiraRestClient() throws URISyntaxException {
        if (restClient == null) {
            URI jiraServerUri = new URI(uri);
            restClient = factory.createWithBasicHttpAuthentication(
                    jiraServerUri, user, pwd);
        }
        return restClient;
    }

    /**
     * 获得工单信息
     *
     * @param issueKey 工单key，比如：NQCP-5
     * @throws URISyntaxException
     */
    public static Issue getIssue(String issueKey) throws URISyntaxException {
        Issue issue = null;
        JiraRestClient restClient = getJiraRestClient();
        // get issue through issueKey
        try {
            issue = restClient.getIssueClient().getIssue(issueKey).claim();

            // 打印工单后续的工作流
            Iterable<Transition> iter = restClient.getIssueClient()
                    .getTransitions(issue).claim();
            for (Transition transition : iter) {
                System.out.println(transition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打印工单明细
        System.out.println(issue);
        return issue;

    }


    /**
     * 检索工单
     *
     * @param jql
     * @return
     * @throws URISyntaxException
     */
    public static Iterable<Issue> searchIssues(String jql) throws URISyntaxException {
        JiraRestClient restClient = getJiraRestClient();
        SearchResult searchResutl = restClient.getSearchClient().searchJql(jql).claim();
        Iterable<Issue> iter = searchResutl.getIssues();
        for (BasicIssue baseIssue : iter) {
            System.out.println(baseIssue);
        }
        return iter;
    }

    /**
     * 检索工单
     *
     * @param jql
     * @param startIndex
     * @param maxResults
     * @return
     * @throws URISyntaxException
     */
    public static Iterable<Issue> searchIssues(String jql, int startIndex, int maxResults) throws URISyntaxException {
        JiraRestClient restClient = getJiraRestClient();
        SearchResult searchResutl = restClient.getSearchClient().searchJql(jql, maxResults, startIndex, null).claim();
        Iterable<Issue> iter = searchResutl.getIssues();
        for (BasicIssue baseIssue : iter) {
            System.out.println(baseIssue);
        }
        return iter;
    }


    /**
     * 打印jira系统中已经创建的全部issueType
     * issuetype/
     *
     * @throws URISyntaxException
     */
    public static Iterable<IssueType> printAllIssueType() throws URISyntaxException {
        JiraRestClient restClient = getJiraRestClient();
        Iterable<IssueType> iter = restClient.getMetadataClient()
                .getIssueTypes().claim();
        for (IssueType issueType : iter) {
            System.out.println(issueType);
        }
        return iter;

    }

    /**
     * 创建一个新工单
     *
     * @param projectKey  项目key，比如：NQCP
     * @param issueType   工单类型，来源于printAllIssueType()的id
     * @param description 工单描述
     * @param summary     工单主题
     * @param assignee    工单负责人
     * @throws URISyntaxException
     */
    public static BasicIssue createIssue(String projectKey, Long issueType,
                                         String description, String summary, String assignee)
            throws URISyntaxException {

        JiraRestClient restClient = getJiraRestClient();

        IssueInputBuilder issueBuilder = new IssueInputBuilder(projectKey,
                issueType);

        issueBuilder.setDescription(description);
        issueBuilder.setSummary(summary);
        if (getUser(assignee) != null) {

            issueBuilder.setAssigneeName(assignee);
        }
        IssueInput issueInput = issueBuilder.build();

        BasicIssue bIssue = null;
        try {
            bIssue = restClient.getIssueClient().createIssue(issueInput)
                    .claim();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(bIssue);
        return bIssue;
    }

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     * @throws URISyntaxException
     */
    public static User getUser(String username) throws URISyntaxException {
        JiraRestClient restClient = getJiraRestClient();
        User user = null;
        try {
            user = restClient.getUserClient().getUser(username).claim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(user);
        return user;
    }

    /**
     * 改变工单workflow状态 issue的workflow是不可以随便改变的，必须按照流程图的顺序进行改变，具体如下：
     * <p>
     * 当前状态 ：说明                      变更流程id:说明 >> 变更后状态
     * 1:open，开放                          1)4:start progress >> in progerss 2)5:resolve issue >> resolved 3)2:close issue >> closed
     * 3:in progerss 正在处理                1)301:stop progress >> open 2)5:resolve issue >> resolved 3)2:close issue >> closed
     * 4:resolved 已解决                     1)701:close issue >> closed 2)3:reopen issue >> reopened
     * 5:reopened 重新打开                   1)4:start progress >> in progerss 2)5:resolve issue >> resolved 3)2:close issue >> closed
     * 6:closed 已关闭                       1)3:reopen issue >> reopened
     * <p>
     * <p>
     * 可通过如下方式查看当前工单的后续工作流程： Iterable<Transition> iter =
     * restClient.getIssueClient().getTransitions(issue).claim();
     * <p>
     * for (Transition transition : iter) { System.out.println(transition); }
     * <p>
     * 输出结果：当前工单状态是 5:reopened 重新打开 Transition{id=4, name=Start Progress,
     * fields=[]} Transition{id=5, name=Resolve Issue,
     * fields=[Field{id=fixVersions, isRequired=false, type=array},
     * Field{id=resolution, isRequired=true, type=resolution}]} Transition{id=2,
     * name=Close Issue, fields=[Field{id=fixVersions, isRequired=false,
     * type=array}, Field{id=resolution, isRequired=true, type=resolution}]}
     *
     * @param issuekey 工单key
     * @param statusId 变更流程id
     * @throws URISyntaxException
     */
    public static void changeIssueStatus(String issuekey, int statusId)
            throws URISyntaxException {

        JiraRestClient restClient = getJiraRestClient();

        Issue issue = getIssue(issuekey);
        if (issue != null) {
            TransitionInput tinput = new TransitionInput(statusId);
            restClient.getIssueClient().transition(issue, tinput);
        }

        // try {
        // Thread.sleep(3000);//因为是异步处理，所以状态是延迟变更的，暂停一下可以看到状态已经变更了
        // issue = getIssue(issuekey);
        // System.out.println(issue.getStatus());
        // } catch (InterruptedException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }

    }

}