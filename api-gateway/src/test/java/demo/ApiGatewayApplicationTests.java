package demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dfwgg.sample.ApiGatewayApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiGatewayApplication.class)
@WebAppConfiguration
public class ApiGatewayApplicationTests {

	@Test
	public void contextLoads() {
	}

}
