package pro.sky.telebotpetshelter.controller;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.telebotpetshelter.entity.animals.Dog;
import pro.sky.telebotpetshelter.repository.DogRepository;
import pro.sky.telebotpetshelter.service.DogServiceImpl;
import pro.sky.telebotpetshelter.utils.Vaccinations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DogController.class)
class DogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DogRepository dogRepository;

    @SpyBean
    private DogServiceImpl dogService;


    private Long id = 1L;
    private String name = "Собака";
    private int age = 2;
    private boolean isHealthy = true;
    private Vaccinations vaccinations = Vaccinations.NO;

    private Dog dogObject() {
        Dog dog = new Dog();
        dog.setId(id);
        dog.setAge(age);
        dog.setName(name);
        dog.setVaccinations(vaccinations);
        dog.setIsHealthy(isHealthy);

        return dog;
    }

    public JSONObject dogJSON() {
        JSONObject dogJSON = new JSONObject();
        dogJSON.put("id", id);
        dogJSON.put("name", name);
        dogJSON.put("age", age);
        dogJSON.put("isHealthy", isHealthy);
        dogJSON.put("vaccinations", vaccinations);
        return dogJSON;
    }

    @Test
    void create() throws Exception {
        when(dogRepository.save(any(Dog.class))).thenReturn(dogObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/dogs")
                        .param("id", String.valueOf(id))
                        .param("name", name)
                        .param("age", String.valueOf(age))
                        .param("isHealthy", String.valueOf(isHealthy))
                        .param("vaccinations", String.valueOf(vaccinations))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.isHealthy").value(isHealthy));

    }

    @Test
    void getByDogId() throws Exception {
        when(dogRepository.findById(any(Long.class))).thenReturn(Optional.of(dogObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs/1")
                        .content(dogJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.isHealthy").value(isHealthy));
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/dogs/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll() throws Exception {
        when(dogRepository.findAll()).thenReturn(new ArrayList<>(List.of(dogObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dogs")
                        .content(dogJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}