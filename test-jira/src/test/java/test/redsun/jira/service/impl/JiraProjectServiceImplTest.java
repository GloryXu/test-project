package test.redsun.jira.service.impl;

import com.atlassian.jira.rest.client.api.domain.BasicProject;
import com.redsun.jira.service.JiraProjectService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.redsun.jira.BaseSpringTest;

import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * Created by xugr on 2017/4/6.
 */
@Slf4j
public class JiraProjectServiceImplTest extends BaseSpringTest {

    @Autowired
    protected JiraProjectService jiraService;

    @Test
    public void testGetAllProjects() {
        try {
            Iterator<BasicProject> basicProjects = jiraService.getAllProjects(restClient);
            if (basicProjects.hasNext()) {
                log.info("--------------:{}", basicProjects.next());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
