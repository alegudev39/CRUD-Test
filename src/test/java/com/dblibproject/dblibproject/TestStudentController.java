package com.dblibproject.dblibproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest

public class TestStudentController {
    @Autowired
    MockMvc mockMvc;



    @Test
    public void testInsertStudent() throws Exception {
        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON).content(
                        """
                                {
                                "name": "Ale",
                                "surname" : "g."
                                }
                                           
                                 """)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.working", is("false")))
                .andExpect(jsonPath("$.name", is("Ale")))
        ;
    }


    @Test
    void testUpdateWorkingStatus() throws Exception {

        mockMvc.perform(put("/students/1/working")
                        .param("working", "false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.working", is(false)));
    }

    @Test
    void testGetAllStudents() throws Exception {

        mockMvc.perform(get("/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is("Ale")))
                .andExpect(jsonPath("$[0].surname", is("G.")))
                .andExpect(jsonPath("$[0].working", is(false)));
    }


}
