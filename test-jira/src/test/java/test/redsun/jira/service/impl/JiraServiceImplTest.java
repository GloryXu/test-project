package test.redsun.jira.service.impl;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.redsun.jira.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import test.redsun.jira.BaseSpringTest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by xugr on 2017/4/6.
 */
@Slf4j
public class JiraServiceImplTest extends BaseSpringTest{

    @Autowired
    JiraRestClientFactory jiraRestClientFactory;

    @Autowired
    JiraService jiraService;

    @Value("${jira.baseURL}")
    String baseURL;

    @Value("${jira.user}")
    String user;

    @Value("${jira.password}")
    String password;

    JiraRestClient restClient = null;

    @Before
    public void init() {
        URI jiraServerUri = null;
        try {
            jiraServerUri = new URI(baseURL);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        restClient = jiraRestClientFactory.createWithBasicHttpAuthentication(jiraServerUri, user, password);
    }

    @Test
    public void testCreateIssue() {
        try {
            Iterator<BasicProject> basicProjects = jiraService.getAllProjects(restClient);
            if (basicProjects.hasNext()) {
                log.info("--------------:{}",basicProjects.next());
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
