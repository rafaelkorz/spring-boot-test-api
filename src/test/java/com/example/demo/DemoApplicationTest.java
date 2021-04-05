package com.example.demo;

import com.example.demo.controller.MoviesController;
import com.example.demo.model.MoviesModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.OrderWith;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DemoApplicationTest {

    public MockMvc mockMvc;

    @Autowired
    private MoviesController controller;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.controller).build();
    }

    @Test
    public void contextLoads() {
        assertThat(this.controller).isNotNull();
    }

    @Test
    public void deleteMovie() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/movies/10"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

}
