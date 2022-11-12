package com.company.demo.service.api.http.rest.controller;

import com.company.demo.BaseTest;
import com.company.demo.service.dto.TemperatureDto;
import com.google.common.base.Charsets;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Main class to test temperature controller layer
 * @author mina.mn@gmail.com
 */
public class TemperatureControllerTest extends BaseTest {
    private final String basePath = "/api/web/temperature/v1";

    @Test
    @Order(1)
    public void readFile() throws Exception {
       super.readResource("exercise.csv", Charsets.UTF_8);
    }

    @Test
    @Order(2)
    public void testCreateRecipe() throws Exception {
        String uri = basePath + "/create";
        String resource = super.readResource("exercise.csv", Charsets.UTF_8);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(resource)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(0, content.length());
    }


    @Test
    @Order(3)
    public void testFindAllTemperatures() throws Exception {
        String uri = basePath + "/findAll";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        TemperatureDto[] temperatures = super.mapFromJson(content, TemperatureDto[].class);
        assertTrue(temperatures.length > 0);
    }

}
