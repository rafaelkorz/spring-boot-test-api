package com.example.demo;

import com.example.demo.model.MoviesModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.jupiter.api.*;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class MoviesTest extends MoviesApplicationTest {

    @Test
    public void importFileCSV() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/movies/load"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    public void createNewMovie() throws Exception {
        MoviesModel movie = new MoviesModel();
        movie.setYear(1990);
        movie.setTitle("test-title");
        movie.setWinner("yes");
        movie.setProducer("test-producer");
        movie.setStudios("test-studios");

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(movie);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/movies/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("studios").value("test-studios"));
    }


    @Test
    public void testlayoutAPI() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/api"))
                .andExpect(MockMvcResultMatchers.jsonPath("min").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("max").exists());
    }

    @Test
    public void updateMovie() throws Exception {
        MoviesModel movie = new MoviesModel(1, 2000, "test1updated", "test2", "test3",  "test4");
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(movie);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/movies/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value("test1updated"));
    }

    @Test
    @Order(6)
    public void findMovie() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value("test1updated"));
    }
}
