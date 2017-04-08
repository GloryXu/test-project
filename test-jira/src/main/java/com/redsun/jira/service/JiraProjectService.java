package com.redsun.jira.service;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueField;
import com.atlassian.jira.rest.client.api.domain.Project;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by xugr on 2017/4/6.
 */
public interface JiraProjectService {

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
     * @param projectKEY
     * @param issueName
     */
    void addIssueComplex(final JiraRestClient restClient, String projectKEY, String issueName);

    /**
     * 获取问题的所有字段
     * @param restClient
     * @param issueKEY
     * @return
     */
    Iterator<IssueField> getIssueFields(final JiraRestClient restClient, String issueKEY) throws ExecutionException, InterruptedException;

    Boolean createProject();
    Boolean updateProject();
    Boolean deleteProject();
    Object getProject();
}
