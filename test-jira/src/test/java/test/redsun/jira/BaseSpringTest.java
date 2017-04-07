package test.redsun.jira;

import com.atlassian.jira.rest.client.JiraRestClientFactory;
import com.redsun.jira.service.JiraProjectService;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试基类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/test-context.xml")
public abstract class BaseSpringTest extends TestCase {

    @Autowired
    protected JiraRestClientFactory jiraRestClientFactory;

    @Autowired
    protected JiraProjectService jiraService;

    @Value("${jira.baseURL}")
    protected String baseURL;

    @Value("${jira.user}")
    protected String user;

    @Value("${jira.password}")
    protected String password;

}
