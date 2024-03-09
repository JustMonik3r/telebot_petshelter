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
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.repository.CatRepository;
import pro.sky.telebotpetshelter.service.CatServiceImpl;
import pro.sky.telebotpetshelter.utils.Vaccinations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatController.class)
class CatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatRepository catRepository;

    @SpyBean
    private CatServiceImpl catService;


    private Long id = 1L;
    private String name = "Кот";
    private int age = 3;
    private boolean isHealthy = true;
    private Vaccinations vaccinations = Vaccinations.YES;

    private Cat catObject() {
        Cat cat = new Cat();
        cat.setId(id);
        cat.setAge(age);
        cat.setName(name);
        cat.setVaccinations(vaccinations);
        cat.setIsHealthy(isHealthy);

        return cat;
    }

    public JSONObject catJSON() {
        JSONObject catJSON = new JSONObject();
        catJSON.put("id", id);
        catJSON.put("name", name);
        catJSON.put("age", age);
        catJSON.put("isHealthy", isHealthy);
        catJSON.put("vaccinations", vaccinations);
        return catJSON;
    }

    @Test
    public void create() throws Exception {
        when(catRepository.save(any(Cat.class))).thenReturn(catObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cats")
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
    void getByCatId() throws Exception {
        when(catRepository.findById(any(Long.class))).thenReturn(Optional.of(catObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/1")
                        .content(catJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.age").value(age))
                .andExpect(jsonPath("$.isHealthy").value(isHealthy));
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/cats/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll() throws Exception {
        when(catRepository.findAll()).thenReturn(new ArrayList<>(List.of(catObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats")
                        .content(catJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}