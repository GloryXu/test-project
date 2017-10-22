package test.redsun;

import com.redsun.event.MyEvent;
import com.redsun.publisher.MyPubisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class PublisherTest {

    @Autowired
    MyPubisher myPubisher;

    @Test
    public void testPublish() {
        myPubisher.publishEvent(new MyEvent("1"));
    }
}
