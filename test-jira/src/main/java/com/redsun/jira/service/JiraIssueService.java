package com.redsun.jira.service;


import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * “问题”服务
 * Created by xugr on 2017/4/7.
 */
public interface JiraIssueService {
    /**
     * 批量创建问题
     *
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
     * 删除问题
     * @param jiraRestClient
     * @param issueKey
     * @param deleteSubtasks 决定是否删除子任务
     * @return
     */
    Boolean deleteIssue(final JiraRestClient jiraRestClient, String issueKey, boolean deleteSubtasks);

    /**
     * 编辑问题
     * @param jiraRestClient
     * @param issue
     * @return
     */
    Boolean editIssue(final JiraRestClient jiraRestClient, Issue issue);

    /**
     * 通过KEY获取问题
     * @param jiraRestClient
     * @param issueKey
     * @return
     */
    Issue findIssueByIssueKey(final JiraRestClient jiraRestClient, String issueKey);

    /**
     * 获取问题的所有字段
     * @param restClient
     * @param issueKEY
     * @return
     */
    Iterator<IssueField> getIssueFields(final JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException;

    /**
     * 添加备注到问题
     * @param jiraRestClient
     * @param issue
     * @param comment
     */
    void addCommentToIssue(final JiraRestClient jiraRestClient, Issue issue, String comment);

    /**
     * 通过标题获取问题
     * @param jiraRestClient
     * @param label
     * @return
     */
    Iterable findIssuesByLabel(final JiraRestClient jiraRestClient, String label);
}
