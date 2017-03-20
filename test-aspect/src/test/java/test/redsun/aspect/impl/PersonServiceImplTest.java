package test.redsun.aspect.impl;

import com.redsun.aspect.PersonServer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.redsun.aspect.BaseSpringTest;

/**
 * Created by xugr on 2017/3/20.
 */
public class PersonServiceImplTest extends BaseSpringTest {

    @Autowired
    PersonServer personServerImpl;

    @Test
    public void save() throws Exception {
        personServerImpl.save("save");
    }

    @Test
    public void update() throws Exception {
        personServerImpl.update("save", new Integer(2));
    }

    @Test
    public void getPersonName() throws Exception {
        personServerImpl.getPersonName(new Integer(3));
    }
}
