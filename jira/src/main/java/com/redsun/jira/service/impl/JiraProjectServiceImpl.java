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
import com.redsun.jira.service.JiraProjectService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by xugr on 2017/4/6.
 */
@Service
public class JiraProjectServiceImpl implements JiraProjectService {

    /**
     * 得到所有项目信息
     *
     * @param restClient
     * @return
     */
    @Override
    public Iterator<BasicProject> getAllProjects(JiraRestClient restClient) throws ExecutionException, InterruptedException {
        Promise<Iterable<BasicProject>> list = restClient.getProjectClient().getAllProjects();
        Iterable<BasicProject> a = list.get();
        Iterator<BasicProject> it = a.iterator();
        return it;
    }

    /**
     * 得到单一项目信息
     *
     * @param restClient
     * @param porjectKEY
     * @return
     */
    @Override
    public Project getProject(JiraRestClient restClient, String porjectKEY) throws ExecutionException, InterruptedException {
        Project project = restClient.getProjectClient().getProject(porjectKEY).get();
        return project;
    }

    @Override
    public Boolean createProject() {
        return null;
    }

    @Override
    public Boolean updateProject() {
        return null;
    }

    @Override
    public Boolean deleteProject() {
        return null;
    }

    @Override
    public Object getProject() {
        return null;
    }
}
