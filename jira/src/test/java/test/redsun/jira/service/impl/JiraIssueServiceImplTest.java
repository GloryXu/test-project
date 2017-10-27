package test.redsun.jira.service.impl;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.input.FieldInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.redsun.jira.service.JiraIssueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.redsun.jira.BaseSpringTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by xugr on 2017/4/8.
 */
@Slf4j
public class JiraIssueServiceImplTest extends BaseSpringTest {

    @Autowired
    protected JiraIssueService jiraIssueService;

    @Test
    public void createIssues() throws Exception {

    }

    @Test
    public void createIssue() throws Exception {
        Map<String, FieldInput> fields = new HashMap<String, FieldInput>();
        FieldInput fieldInput = new FieldInput(IssueFieldId.ISSUE_TYPE_FIELD, "issuetype");
        fields.put("fieldInput", fieldInput);
        IssueInput issueInput = new IssueInput(fields);
        BasicIssue basicIssue = jiraIssueService.createIssue(restClient, issueInput);
        log.info("--------------------{}", basicIssue);
    }

    @Test
    public void addIssue() throws Exception {

    }

    @Test
    public void addIssueComplex() throws Exception {

    }

    @Test
    public void getIssue() throws Exception {

    }

    @Test
    public void deleteIssue() throws Exception {
        jiraIssueService.deleteIssue(restClient, "" ,true);
    }

    @Test
    public void editIssue() throws Exception {

    }

    @Test
    public void findIssueByIssueKey() throws Exception {

    }

    @Test
    public void getIssueFields() throws Exception {

    }

    @Test
    public void addCommentToIssue() throws Exception {

    }

    @Test
    public void findIssuesByLabel() throws Exception {

    }
}