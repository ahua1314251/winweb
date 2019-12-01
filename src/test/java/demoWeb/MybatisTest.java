package demoWeb;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.winker.winweb.application.Application;


@RunWith(SpringRunner.class)
/*@ConfigurationProperties(prefix="application")*/
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@EnableAutoConfiguration
public class MybatisTest {
    @Test
    public void test(){
System.out.println("hello");
    }

}

