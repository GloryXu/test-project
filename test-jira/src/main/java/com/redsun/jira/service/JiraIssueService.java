package com.redsun.jira.service;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.BasicIssue;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.input.IssueInput;

import java.util.concurrent.ExecutionException;

/**
 * “问题”服务
 * Created by xugr on 2017/4/7.
 */
public interface JiraIssueService {
    /**
     * 批量创建问题
     * @param jiraRestClient
     * @param newIssue
     * @return
     */
    BasicIssue createIssues(final JiraRestClient jiraRestClient, IssueInput newIssue);
    /**
     * 创建问题
     * @param jiraRestClient
     * @param newIssue
     * @return
     */
    BasicIssue createIssue(final JiraRestClient jiraRestClient, IssueInput newIssue);

    /**
     * 得到单一问题信息
     * @param restClient
     * @param issueKEY
     * @return
     */
    Issue getIssue(final JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException;

    /**
     * 删除问题，目前找不到对应API
     * @param jiraRestClient
     * @param issue
     */
    Boolean deleteIssue(final JiraRestClient jiraRestClient, Issue issue);

    /**
     * 编辑问题
     * @param jiraRestClient
     * @param issue
     * @return
     */
    Boolean editIssue(final JiraRestClient jiraRestClient, Issue issue);
}
