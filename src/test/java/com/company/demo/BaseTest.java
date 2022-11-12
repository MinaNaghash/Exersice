package com.company.demo;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Resources;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class BaseTest {
    private final String FILE_TYPE = ".csv";

    @Autowired
    WebApplicationContext webApplicationContext;
    protected MockMvc mockMvc;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    protected void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    /**
     * Map to json string.
     *
     * @param obj the obj
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    /**
     * Map from json t.
     *
     * @param <T>   the type parameter
     * @param json  the json
     * @param clazz the clazz
     * @return the t
     * @throws IOException the io exception
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    protected String readResource(final String fileName, Charset charset) throws Exception {
        try {
            return Resources.toString(Resources.getResource(fileName), charset);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
