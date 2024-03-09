package pro.sky.telebotpetshelter.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.entity.DogShelter;
import pro.sky.telebotpetshelter.repository.CatShelterRepository;
import pro.sky.telebotpetshelter.repository.DogShelterRepository;
import pro.sky.telebotpetshelter.service.ShelterServiceImpl_Cat;
import pro.sky.telebotpetshelter.service.ShelterServiceImpl_Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogShelterController.class)
class DogShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private DogShelterRepository dogShelterRepository;

    @SpyBean
    private ShelterServiceImpl_Dog dogShelterService;


    private Long id = 1L;
    private String name = "Приют для собак";
    private String location = "location";
    private String timetable = "timetable";
    private String about_shelter = "about_shelter";
    private String security = "security";
    private String safetyMeasures = "safety measures";

    private DogShelter dogShelterObject() {
        DogShelter dogShelter = new DogShelter();
        dogShelter.setDogId(1L);
        dogShelter.setId(id);
        dogShelter.setName(name);
        dogShelter.setLocation(location);
        dogShelter.setSecurity(security);
        dogShelter.setAboutShelter(about_shelter);
        dogShelter.setTimetable(timetable);
        dogShelter.setSafetyMeasures(safetyMeasures);
        return dogShelter;
    }

    public JSONObject catShelterJSON() {
        JSONObject catShelterJSON = new JSONObject();
        catShelterJSON.put("id", 1L);
        catShelterJSON.put("name", name);
        catShelterJSON.put("location", location);
        catShelterJSON.put("security", security);
        catShelterJSON.put("about_shelter", about_shelter);
        catShelterJSON.put("timetable", timetable);
        catShelterJSON.put("safetyMeasures", safetyMeasures);
        return catShelterJSON;
    }

    @Test
    public void create() throws Exception {
        when(dogShelterRepository.save(ArgumentMatchers.any(DogShelter.class))).thenReturn(dogShelterObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dogs/shelters")
                        .param("name", String.valueOf(name))
                        .param("location", String.valueOf(location))
                        .param("timetable", String.valueOf(timetable))
                        .param("about_shelter", String.valueOf(about_shelter))
                        .param("security", String.valueOf(security))
                        .param("safetyMeasures", String.valueOf(safetyMeasures))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value(location))
                .andExpect(MockMvcResultMatchers.jsonPath("$.security").value(security));
    }
    @Test
    void update() {
    }

    @Test
    public void getAll() throws Exception {
        when(dogShelterRepository.findAll()).thenReturn(new ArrayList<>(List.of(dogShelterObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/shelters")
                        .content(catShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }



    @Test
    void getShelterId() throws Exception {
        when(dogShelterRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(dogShelterObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/shelters/1")
                        .content(catShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value(location))
                .andExpect(MockMvcResultMatchers.jsonPath("$.security").value(security));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dogShelter/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}