package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@Transactional
@AutoConfigureMockMvc
class DemoApplicationTests {
    @Autowired
    MockMvc mockMvc;
    @Test
    public void getTest01() throws Exception {
        String responseString=this.mockMvc.perform(MockMvcRequestBuilders.get("/staff/1")
                                .contentType("application/json;charset=UTF-8"))
                                .andExpect(status().isOk())
                                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        String expected="{\"errno\":0,\"data\":{\"name\":\"abc\",\"salary\":2000},\"errmsg\":\"success\"}";
        JSONAssert.assertEquals(responseString,expected,true);
    }
    @Test
    public void getTest02()throws Exception{
        String responseString=this.mockMvc.perform(MockMvcRequestBuilders.get("/staff/1000")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        String expected="{\"errno\":404,\"data\":null,\"errmsg\":\"员工id不存在\"}";
        JSONAssert.assertEquals(responseString,expected,true);
    }
    @Test
    public void postTest01()throws Exception{
        String json="{\"name\":\"abc\", \"salary\":\"2000\"}";
        String responseString=this.mockMvc.perform(MockMvcRequestBuilders.post("/staff")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        System.out.println(responseString);
    }
}
