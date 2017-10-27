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

    Boolean createProject();
    Boolean updateProject();
    Boolean deleteProject();
    Object getProject();
}
