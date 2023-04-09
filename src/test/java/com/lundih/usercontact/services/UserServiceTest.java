package com.lundih.usercontact.services;

import com.lundih.usercontact.dtos.requests.UserEditRequest;
import com.lundih.usercontact.dtos.requests.UserRequest;
import com.lundih.usercontact.dtos.responses.PageResponse;
import com.lundih.usercontact.dtos.responses.UserResponse;
import com.lundih.usercontact.entities.User;
import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import com.lundih.usercontact.exceptions.DuplicateEntryException;
import com.lundih.usercontact.exceptions.UserNotFoundException;
import com.lundih.usercontact.mappers.UserMapper;
import com.lundih.usercontact.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {
    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    private final UserService userService = new UserService(userRepository, userMapper);

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setCountry(Country.ICELAND);
        user.setNationality(Nationality.ICELANDIC);
        user.setBirthDay(Instant.EPOCH);
        user.setGender(Gender.FEMALE);
        user.setNationalId("nationalId");
        user.setCreatedOn(Instant.now());
        user.setUpdatedOn(Instant.now());
    }

    @Test
    void createUser_should_throw_an_exception_when_a_duplicate_is_found() {
        String nationalId = "nationalId";
        Nationality nationality = Nationality.ICELANDIC;
        UserRequest request = new UserRequest("firstName", "lastName", Country.ICELAND, nationality,
                Instant.now(), Gender.FEMALE, nationalId);

        Mockito.when(userRepository.existsByNationalIdAndNationality(nationalId, nationality)).thenReturn(true);

        Assertions.assertThrows(DuplicateEntryException.class, () -> userService.createUser(request));
    }

    @Test
    void createUser_should_throw_invoke_save_in_the_repository() {
        UserRequest request = new UserRequest("firstName", "lastName", Country.ICELAND, Nationality.ICELANDIC,
                Instant.now(), Gender.FEMALE, "nationalId");

        Mockito.when(userRepository.existsByNationalIdAndNationality(any(), any())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponse response = userService.createUser(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void editUser_should_throw_an_exception_if_id_is_not_found() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.editUser(1L, new UserEditRequest()));
    }

    @Test
    void editUser_should_edit_provided_values() {
        String firstName = "newFirstName";
        String lastName = "newLastName";
        Country country = Country.GEORGIA;
        Nationality nationality = Nationality.GEORGIAN;
        Instant birthDay = Instant.EPOCH.plusMillis(84600);
        Gender gender = Gender.MALE;
        String nationalId = "newNationalId";

        UserEditRequest request = new UserEditRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setCountry(country);
        request.setNationality(nationality);
        request.setBirthDay(birthDay);
        request.setGender(gender);
        request.setNationalId(nationalId);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsByNationalIdAndNationality(any(), any())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponse userResponse = userService.editUser(1L, request);

        Assertions.assertEquals(firstName, userResponse.getFirstName());
        Assertions.assertEquals(lastName, userResponse.getLastName());
        Assertions.assertEquals(country.toString(), userResponse.getCountry());
        Assertions.assertEquals(nationality.toString(), userResponse.getNationality());
        Assertions.assertEquals(birthDay, userResponse.getBirthDay());
        Assertions.assertEquals(gender.toString(), userResponse.getGender());
        Assertions.assertEquals(nationalId, userResponse.getNationalId());
    }

    @Test
    void editUser_should_not_edit_values_not_provided() {
        String firstName = "";
        String lastName = null;
        Country country = null;
        Nationality nationality = null;
        Instant birthDay = null;
        Gender gender = null;
        String nationalId = "";

        UserEditRequest request = new UserEditRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setCountry(country);
        request.setNationality(nationality);
        request.setBirthDay(birthDay);
        request.setGender(gender);
        request.setNationalId(nationalId);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsByNationalIdAndNationality(any(), any())).thenReturn(false);
        Mockito.when(userRepository.save(any())).thenReturn(user);

        UserResponse userResponse = userService.editUser(1L, request);

        Assertions.assertNotEquals(firstName, userResponse.getFirstName());
        Assertions.assertNotEquals(lastName, userResponse.getLastName());
        Assertions.assertNotEquals(country, user.getCountry());
        Assertions.assertNotEquals(nationality, user.getNationality());
        Assertions.assertNotEquals(birthDay, userResponse.getBirthDay());
        Assertions.assertNotEquals(gender, user.getGender());
        Assertions.assertNotEquals(nationalId, userResponse.getNationalId());
    }

    @Test
    void editUser_should_throw_an_exception_when_a_duplicate_is_found() {
        String nationalId = "nationalId";
        Nationality nationality = Nationality.ICELANDIC;
        UserEditRequest request = new UserEditRequest();
        request.setNationality(nationality);
        request.setNationalId(nationalId);

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.existsByNationalIdAndNationality(nationalId, nationality)).thenReturn(true);

        Assertions.assertThrows(DuplicateEntryException.class, () -> userService.editUser(1L, request));
    }

    @Test
    void getUserById_should_throw_an_exception_if_id_is_not_found() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void getUserById_should_return_user_response() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Assertions.assertNotNull(userService.getUserById(1L));
    }

    @Test
    void getUsers_should_return_a_pageResponse() {
        Mockito.when(userRepository.findBy(any())).thenReturn(Page.empty());

        Assertions.assertNotNull(userService.getUsers(0, 20, null, null));
        Assertions.assertNotNull(userService.getUsers(0, 20, List.of(), false));
        Assertions.assertNotNull(userService.getUsers(0, 20, List.of("createdOn"), true));
    }

    @Test
    void searchUsers_should_return_results() {
        Mockito.when(userRepository.findAll(any(), any())).thenReturn(List.of(user));

        Assertions.assertNotNull(userService.searchUsers("test"));
    }

    @Test
    void getValues_should_return_a_map() {
        Assertions.assertNotNull(userService.getNationalities());
        Assertions.assertNotNull(userService.getCountries());
        Assertions.assertNotNull(userService.getGenders());
    }
}
