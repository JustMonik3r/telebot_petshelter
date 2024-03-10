package pro.sky.telebotpetshelter.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;
import pro.sky.telebotpetshelter.entity.CatShelter;
import pro.sky.telebotpetshelter.entity.animals.Cat;
import pro.sky.telebotpetshelter.repository.CatShelterRepository;
import pro.sky.telebotpetshelter.service.ShelterService;
import pro.sky.telebotpetshelter.service.ShelterServiceImpl_Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.constant.ConstantDescs.DEFAULT_NAME;
import static java.lang.reflect.Array.get;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CatShelterController.class)
class CatShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private CatShelterRepository catShelterRepository;
    CatShelter catShelterDefault;

    @SpyBean
    private ShelterServiceImpl_Cat catShelterService;


    @BeforeEach
    void init() {
        catShelterDefault = new CatShelter();
        catShelterDefault.setId(1L);
        catShelterDefault.setName(DEFAULT_NAME);
    }

    private Long id = 1L;
    private String name = "Кошачий приют";
    private String location = "location";
    private String timetable = "timetable";
    private String aboutShelter = "about shelter";
    private String security = "security";
    private String safetyMeasures = "safety measures";

    private CatShelter catShelterObject() {
        CatShelter catShelter = new CatShelter();
        catShelter.setCatId(1L);
        catShelter.setId(id);
        catShelter.setName(name);
        catShelter.setLocation(location);
        catShelter.setSecurity(security);
        catShelter.setAboutShelter(aboutShelter);
        catShelter.setTimetable(timetable);
        catShelter.setSafetyMeasures(safetyMeasures);
        return catShelter;
    }
    public JSONObject catShelterJSON() {
        JSONObject catShelterJSON = new JSONObject();
        catShelterJSON.put("cat_id", 1L);
        catShelterJSON.put("name", name);
        catShelterJSON.put("location", location);
        catShelterJSON.put("security", security);
        catShelterJSON.put("aboutShelter", aboutShelter);
        catShelterJSON.put("timetable", timetable);
        catShelterJSON.put("safetyMeasures", safetyMeasures);
        return catShelterJSON;
    }

    @Test
    public void saveCatShelterTest() throws Exception {
        when(catShelterRepository.save(catShelterDefault)).thenReturn(catShelterDefault);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/cats/shelters")
                        .param("id", String.valueOf(id))
                        .param("name", String.valueOf(name))
                        .param("location", String.valueOf(location))
                        .param("timetable", String.valueOf(timetable))
                        .param("aboutShelter", String.valueOf(aboutShelter))
                        .param("security", String.valueOf(security))
                        .param("safetyMeasures", String.valueOf(safetyMeasures))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.name").value(name))
                .andExpect((ResultMatcher) jsonPath("$.location").value(location))
                .andExpect((ResultMatcher) jsonPath("$.security").value(security));
    }

    @Test
    public void getAllTest() throws Exception {
        when(catShelterRepository.findAll()).thenReturn(new ArrayList<>(List.of(catShelterObject())));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/cats/shelters")
                        .content(catShelterJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getShelter() throws Exception {
        when(catShelterRepository.findById(anyLong())).thenReturn(Optional.ofNullable(catShelterDefault));
        this.mockMvc.perform((RequestBuilder) get("/catShelter/{id}", Math.toIntExact(1L)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", is(catShelterDefault.getId())))
                .andExpect((ResultMatcher) jsonPath("$.name", is(catShelterDefault.getName())));
    }

    @Test
    void deleteById() throws Exception {
        doNothing().when(catShelterRepository).deleteById(anyLong());
        this.mockMvc.perform(delete("/catShelter/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}