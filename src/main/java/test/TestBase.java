package test;

import com.rockfintech.reas.xabank.MainApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
//指定SpringBoot工程的Application启动类
//支持web项目
@WebAppConfiguration
@SpringBootTest(classes = MainApplication.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class TestBase {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void init() {
       logger.info("----------------junit test start-------------------");
    }

    @After
    public void after() {
        logger.info("----------------junit test end-------------------");
    }
}
