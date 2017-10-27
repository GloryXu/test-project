package com.redsun.jira.service.impl;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.util.concurrent.Promise;
import com.redsun.jira.service.JiraIssueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by xugr on 2017/4/8.
 */
@Service
public class JiraIssueServiceImpl implements JiraIssueService {

    private static final long TASK_TYPE_ID = 3L; // JIRA magic value

    /**
     * 批量创建问题
     *
     * @param jiraRestClient
     * @param newIssue
     * @return
     */
    @Override
    public BasicIssue createIssues(JiraRestClient jiraRestClient, IssueInput newIssue) {
        return null;
    }

    /**
     * 创建问题
     *
     * @param jiraRestClient
     * @param newIssue
     * @return
     */
    @Override
    public BasicIssue createIssue(JiraRestClient jiraRestClient, IssueInput newIssue) {
        BasicIssue basicIssue = jiraRestClient.getIssueClient().createIssue(newIssue).claim();
        return basicIssue;
    }

    /**
     * 创建问题 ：仅有简单问题名称
     *
     * @param restClient
     * @param projectKEY
     * @param issueName
     */
    public void addIssue(JiraRestClient restClient, String projectKEY, String issueName) {
        IssueInputBuilder builder = new IssueInputBuilder(projectKEY, TASK_TYPE_ID, issueName);
        builder.setDescription("issue description");
        final IssueInput input = builder.build();

        // create issue
        final IssueRestClient client = restClient.getIssueClient();
        final BasicIssue issue = client.createIssue(input).claim();
        final Issue actual = client.getIssue(issue.getKey()).claim();
    }

    /**
     * 创建问题 ：包含自定义字段
     *
     * @param restClient
     * @param porjectKEY
     * @param issueName
     */
    public void addIssueComplex(JiraRestClient restClient, String porjectKEY, String issueName) {
        IssueInputBuilder builder = new IssueInputBuilder(porjectKEY, TASK_TYPE_ID, issueName);
        builder.setDescription("issue description");
        // builder.setFieldValue("priority", ComplexIssueInputFieldValue.with("name", PRIORITY));
        //单行文本
        builder.setFieldValue("customfield_10042", "单行文本测试");

        //单选字段
        builder.setFieldValue("customfield_10043", ComplexIssueInputFieldValue.with("value", "一般"));

        //数值自定义字段
        builder.setFieldValue("customfield_10044", 100.08);

        //用户选择自定义字段
        builder.setFieldValue("customfield_10045", ComplexIssueInputFieldValue.with("name", "admin"));
        //用户选择自定义字段(多选)
        Map<String, Object> user1 = new HashMap<String, Object>();
        user1.put("name", "admin");
        Map<String, Object> user2 = new HashMap<String, Object>();
        user2.put("name", "wangxn");
        ArrayList peoples = new ArrayList();
        peoples.add(user1);
        peoples.add(user2);
        builder.setFieldValue("customfield_10047", peoples);

        //设定父问题
        Map<String, Object> parent = new HashMap<String, Object>();
        parent.put("key", "FEEDBACK-25");
        FieldInput parentField = new FieldInput("parent", new ComplexIssueInputFieldValue(parent));
        builder.setFieldInput(parentField);

        final IssueInput input = builder.build();
        final IssueRestClient client = restClient.getIssueClient();
        final BasicIssue issue = client.createIssue(input).claim();
        final Issue actual = client.getIssue(issue.getKey()).claim();
    }

    /**
     * 得到单一问题信息
     *
     * @param restClient
     * @param issueKEY
     * @return
     */
    @Override
    public Issue getIssue(JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException {
        Promise<Issue> list = restClient.getIssueClient().getIssue(issueKEY);
        Issue issue = list.get();
        return issue;
    }

    /**
     * 删除问题
     * @param jiraRestClient
     * @param issueKey
     * @param deleteSubtasks 决定是否删除子任务
     * @return
     */
    @Override
    public Boolean deleteIssue(JiraRestClient jiraRestClient, String issueKey, boolean deleteSubtasks) {
        IssueRestClient issueRestClient = jiraRestClient.getIssueClient();
        Promise<Void> promise = issueRestClient.deleteIssue(issueKey, deleteSubtasks);
        return null;
    }

    /**
     * 编辑问题
     *
     * @param jiraRestClient
     * @param issue
     * @return
     */
    @Override
    public Boolean editIssue(JiraRestClient jiraRestClient, Issue issue) {
        return null;
    }

    /**
     * 通过KEY获取问题
     *
     * @param jiraRestClient
     * @param issueKey
     * @return
     */
    @Override
    public Issue findIssueByIssueKey(JiraRestClient jiraRestClient, String issueKey) {
        SearchRestClient searchClient = jiraRestClient.getSearchClient();
        String jql = "issuekey = \"" + issueKey + "\"";
        SearchResult results = searchClient.searchJql(jql).claim();
        return (Issue) results.getIssues().iterator().next();
    }

    /**
     * 获取问题的所有字段
     *
     * @param restClient
     * @param issueKEY
     * @return
     */
    @Override
    public Iterator<IssueField> getIssueFields(JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException {
        Promise<Issue> list = restClient.getIssueClient()
                .getIssue(issueKEY);
        Issue issue = list.get();
        Iterable<IssueField> fields = issue.getFields();
        Iterator<IssueField> it = fields.iterator();
        return it;
    }

    /**
     * 添加备注到问题
     *
     * @param jiraRestClient
     * @param issue
     * @param comment
     */
    @Override
    public void addCommentToIssue(JiraRestClient jiraRestClient, Issue issue, String comment) {
        IssueRestClient issueClient = jiraRestClient.getIssueClient();
        issueClient.addComment(issue.getCommentsUri(), Comment.valueOf(comment)).claim();
    }

    /**
     * 通过标题获取问题
     *
     * @param jiraRestClient
     * @param label
     * @return
     */
    @Override
    public Iterable findIssuesByLabel(JiraRestClient jiraRestClient, String label) {
        String jql = "labels%3D" + label;
        SearchResult results = ((SearchRestClient) jiraRestClient).searchJql(jql).claim();
        return results.getIssues();
    }
}
