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
import pro.sky.telebotpetshelter.entity.PetOwner;
import pro.sky.telebotpetshelter.repository.PetOwnerRepository;
import pro.sky.telebotpetshelter.service.PetOwnerServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PetOwnerController.class)
class PetOwnerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PetOwnerRepository petOwnerRepository;

    @SpyBean
    private PetOwnerServiceImpl petOwnerService;

    private Long telegramId = 1L;
    private String name = "Усыновитель";
    private Long phoneNumber = 79903332211L;
    private String email = "petowner@mail.ru";
    private Boolean tookAnAnimal = true;

    private PetOwner petOwnerObject() {
        PetOwner petOwner = new PetOwner();
        //petOwner.setId(1L);
        petOwner.setTelegramId(telegramId);
        petOwner.setName(name);
        petOwner.setEmail(email);
        petOwner.setPhoneNumber(phoneNumber);
        petOwner.setTookAnAnimal(tookAnAnimal);
        return petOwner;
    }
    public JSONObject petOwnerJSON() {
        JSONObject petOwnerObject = new JSONObject();
        petOwnerObject.put("telegramId", telegramId);
        petOwnerObject.put("name", name);
        petOwnerObject.put("phoneNumber", phoneNumber);
        petOwnerObject.put("email", email);
        petOwnerObject.put("tookAnAnimal", tookAnAnimal);

        return petOwnerObject;
    }
    @Test
    void addOwner() throws Exception {
        when(petOwnerRepository.save(any(PetOwner.class))).thenReturn(petOwnerObject());

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/owner")
                        .param("telegramId", String.valueOf(telegramId))
                        .param("name", name)
                        .param("phoneNumber", String.valueOf(phoneNumber))
                        .param("email", String.valueOf(email))
                        .param("tookAnAnimal", String.valueOf(tookAnAnimal))
                        .content(petOwnerJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    void updateOwnerInfo() {


    }

    @Test
    void getOwnerById() throws Exception {
        when(petOwnerRepository.findById(any(Long.class))).thenReturn(Optional.of(petOwnerObject()));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/owner/1")
                        .content(petOwnerJSON().toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value(phoneNumber))
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    void getAllOwners() throws Exception {
        when(petOwnerRepository.findAll()).thenReturn(List.of(petOwnerObject()));
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/owner/all")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOwner() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/owner/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}