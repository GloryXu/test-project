package com.redsun.jira.service;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.*;
import com.atlassian.jira.rest.client.domain.input.IssueInput;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by xugr on 2017/4/6.
 */
public interface JiraService {

    /**
     * 得到所有项目信息
     * @param restClient
     * @return
     */
    Iterator<BasicProject> getAllProjects(final JiraRestClient restClient) throws ExecutionException, InterruptedException;

    /**
     * 得到单一项目信息
     * @param restClient
     * @param porjectKEY
     * @return
     */
    Project getProject(final JiraRestClient restClient, String porjectKEY) throws ExecutionException, InterruptedException;

    /**
     * 得到单一问题信息
     * @param restClient
     * @param issueKEY
     * @return
     */
    Issue getIssue(final JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException;

    /**
     * 创建问题
     * @param jiraRestClient
     * @param newIssue
     * @return
     */
    BasicIssue createIssue(final JiraRestClient jiraRestClient, IssueInput newIssue);

    /**
     * 添加备注到问题
     * @param jiraRestClient
     * @param issue
     * @param comment
     */
    void addCommentToIssue(final JiraRestClient jiraRestClient,Issue issue, String comment);

    /**
     * 删除问题，目前找不到对应API
     * @param jiraRestClient
     * @param issue
     */
    void deleteIssue(final JiraRestClient jiraRestClient, Issue issue);

    /**
     * 通过标题获取问题
     * @param jiraRestClient
     * @param label
     * @return
     */
    Iterable findIssuesByLabel(final JiraRestClient jiraRestClient, String label);

    /**
     * 通过KEY获取问题
     * @param jiraRestClient
     * @param issueKey
     * @return
     */
    Issue findIssueByIssueKey(final JiraRestClient jiraRestClient, String issueKey);

    /**
     * 创建问题 ：仅有简单问题名称
     * @param restClient
     * @param projectKEY
     * @param issueName
     */
    void addIssue(final JiraRestClient restClient, String projectKEY, String issueName);

    /**
     * 创建问题 ：包含自定义字段
     * @param restClient
     * @param porjectKEY
     * @param issueName
     */
    void addIssueComplex(final JiraRestClient restClient, String porjectKEY, String issueName);

    /**
     * 获取问题的所有字段
     * @param restClient
     * @param issueKEY
     * @return
     */
    Iterator<Field> getIssueFields(final JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException;
}
