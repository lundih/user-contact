package com.lundih.usercontact.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lundih.usercontact.dtos.requests.UserEditRequest;
import com.lundih.usercontact.dtos.requests.UserRequest;
import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import com.lundih.usercontact.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
class UserResourceTest {
    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Autowired
    public UserResourceTest(MockMvc mockMvc, ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @Test
    public void createUser_should_invoke_createUser_in_service() throws Exception {
        UserRequest request = new UserRequest("firstName", "lastName", Country.JAPAN,Nationality.JAPANESE,
                Instant.EPOCH, Gender.FEMALE, "nationId");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).createUser(any());
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void editUser_should_invoke_editUser_in_service() throws Exception {
        UserEditRequest request = new UserEditRequest();
        request.setFirstName("firstName");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).editUser(any(), any());
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public  void getUserById_should_invoke_getUserById_in_service() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).getUserById(1L);
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void getUsers_should_invoke_getUsers_in_service() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users?pageNumber=0&pageSize=20"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).getUsers(0, 20, null, null);
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void searchUsers_should_invoke_searchUsers_in_service() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/search?query=test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).searchUsers("test");
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void getNationalities_should_invoke_getNationalities_in_service() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/nationalities"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).getNationalities();
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void getCountries_should_invoke_getCountries_in_service() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/countries"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).getCountries();
        Assertions.assertNotNull(mvcResult.getResponse());
    }

    @Test
    public void getGenders_should_invoke_getGenders_in_service() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/genders"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Mockito.verify(userService, Mockito.times(1)).getGenders();
        Assertions.assertNotNull(mvcResult.getResponse());
    }
}