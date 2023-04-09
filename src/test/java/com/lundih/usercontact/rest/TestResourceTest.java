package com.lundih.usercontact.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestResourceTest {
    private final MockMvc mockMvc;

    @Autowired
    public TestResourceTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testApp_should_return_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tests"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Assertions.assertNotNull(mvcResult.getResponse());
    }
}
