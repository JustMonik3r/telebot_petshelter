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
import pro.sky.telebotpetshelter.repository.CatShelterRepository;
import pro.sky.telebotpetshelter.service.ShelterServiceImpl_Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatShelterController.class)
class CatShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CatShelterRepository catShelterRepository;
    //CatShelter catShelterDefault;

    @SpyBean
    private ShelterServiceImpl_Cat catShelterService;


//    @BeforeEach
//    void init() {
//        catShelterDefault = new CatShelter();
//        catShelterDefault.setId(1L);
//        catShelterDefault.setName(DEFAULT_NAME);
//    }

    private Long id = 1L;
    private String name = "Кошачий приют";
    private String location = "location";
    private String timetable = "timetable";
    private String about_shelter = "about_shelter";
    private String security = "security";
    private String safetyMeasures = "safety measures";

    private CatShelter catShelterObject() {
        CatShelter catShelter = new CatShelter();
        catShelter.setCatId(1L);
        catShelter.setId(id);
        catShelter.setName(name);
        catShelter.setLocation(location);
        catShelter.setSecurity(security);
        catShelter.setAboutShelter(about_shelter);
        catShelter.setTimetable(timetable);
        catShelter.setSafetyMeasures(safetyMeasures);
        return catShelter;
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
        when(catShelterRepository.save(ArgumentMatchers.any(CatShelter.class))).thenReturn(catShelterObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cats/shelters")
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
        when(catShelterRepository.findAll()).thenReturn(new ArrayList<>(List.of(catShelterObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/shelters/all")
                        .content(catShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }



    @Test
    void getShelterId() throws Exception {
        when(catShelterRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(catShelterObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/shelters/1")
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
                        .delete("/catShelter/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
