package cn.androidminds.springstudy.web;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTest {

    private TestRestTemplate template = new TestRestTemplate();
    //@Value("${server.port}")// 注入端口号
    private int port = 8080;

    @Test
    public void testLogin(){
        String url = "http://localhost:"+port+"/loginCheck.html";
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("userName", "admin");
        map.add("password", "123456");
        String result = template.postForObject(url, map, String.class);
        System.out.println(result);
        assertNotNull(result);
        assertThat(result, Matchers.containsString("admin"));
    }

}
