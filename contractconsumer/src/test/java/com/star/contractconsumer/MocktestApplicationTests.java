package com.star.contractconsumer;

import com.star.contractconsumer.model.User;
import com.star.contractconsumer.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContractconsumerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(ids = {"com.star:contractprovider:+:stubs:8080"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class MocktestApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HelloService helloService;

    /**
     * RestTemplate方式mock契约测试
     *
     * @throws Exception
     */
    @Test
    public void should_be_test_rest_template_mock() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/hello")
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("id", "user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        assertEquals("hello user", mockHttpServletResponse.getContentAsString());

    }

    /**
     * FeignClient方式mock契约测试
     *
     * @throws Exception
     */
    @Test
    public void should_be_test_feign_client_mock() throws Exception {
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders
                .get("/hello2")
                .header("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("id", "user"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        assertEquals("hello user", mockHttpServletResponse.getContentAsString());
    }

    @Test
    public void should_insert_user_success() throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        User user = User.builder().id(123L).name("star").age(24).address("湖北武汉").build();
        MockHttpServletResponse mockHttpServletResponse = mockMvc.perform(MockMvcRequestBuilders.post("/addUser")
                .header("Content-Type", "application/json;charset=UTF-8")
                .content(mapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        assertEquals("添加用户成功", mockHttpServletResponse.getContentAsString());

    }

}
