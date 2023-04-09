package com.lundih.usercontact.mappers;

import com.lundih.usercontact.dtos.requests.UserRequest;
import com.lundih.usercontact.dtos.responses.UserResponse;
import com.lundih.usercontact.entities.User;
import com.lundih.usercontact.enums.Country;
import com.lundih.usercontact.enums.Gender;
import com.lundih.usercontact.enums.Nationality;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.List;

public class UserMapperTest {
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void requestToUser_should_map_request_to_user() {
        UserRequest request = new UserRequest("firstName", "lastName", Country.AUSTRIA,
                Nationality.AUSTRIAN, Instant.EPOCH, Gender.FEMALE, "nationalId");
        User user = userMapper.requestToUser(request);

        Assertions.assertEquals(request.getFirstName(), user.getFirstName());
        Assertions.assertEquals(request.getLastName(), user.getLastName());
        Assertions.assertEquals(request.getCountry(), user.getCountry());
        Assertions.assertEquals(request.getNationality(), user.getNationality());
        Assertions.assertEquals(request.getBirthDay(), user.getBirthDay());
        Assertions.assertEquals(request.getGender(), user.getGender());
        Assertions.assertEquals(request.getNationalId(), user.getNationalId());
    }

    @Test
    public void userToResponse_should_map_user_to_response() {
        User user = new User();
        user.setId(0L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setCountry(Country.NEW_ZEALAND);
        user.setNationality(Nationality.NEW_ZEALANDER);
        user.setBirthDay(Instant.EPOCH);
        user.setGender(Gender.MALE);
        user.setNationalId("nationalId");
        user.setCreatedOn(Instant.now());
        user.setUpdatedOn(Instant.now());
        UserResponse response = userMapper.userToResponse(user);

        Assertions.assertEquals(user.getId(), response.getId());
        Assertions.assertEquals(user.getFirstName(), response.getFirstName());
        Assertions.assertEquals(user.getLastName(), response.getLastName());
        Assertions.assertEquals(user.getCountry().toString(), response.getCountry());
        Assertions.assertEquals(user.getNationality().toString(), response.getNationality());
        Assertions.assertEquals(user.getBirthDay(), response.getBirthDay());
        Assertions.assertEquals(user.getGender().toString(), response.getGender());
        Assertions.assertEquals(user.getNationalId(), response.getNationalId());
        Assertions.assertEquals(user.getCreatedOn(), response.getCreatedOn());
        Assertions.assertEquals(user.getUpdatedOn(), response.getUpdatedOn());
    }

    @Test
    public void userListToResponse_should_map_user_list_to_response_list() {
        User user = new User();
        user.setId(0L);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setCountry(Country.NEW_ZEALAND);
        user.setNationality(Nationality.NEW_ZEALANDER);
        user.setBirthDay(Instant.EPOCH);
        user.setGender(Gender.MALE);
        user.setNationalId("nationalId");
        user.setCreatedOn(Instant.now());
        user.setUpdatedOn(Instant.now());
        User user2 = new User();
        user2.setId(1L);
        user2.setFirstName("firstName");
        user2.setLastName("lastName");
        user2.setCountry(Country.NEW_ZEALAND);
        user2.setNationality(Nationality.NEW_ZEALANDER);
        user2.setBirthDay(Instant.EPOCH);
        user2.setGender(Gender.MALE);
        user2.setNationalId("nationalId2");
        user2.setCreatedOn(Instant.now());
        user2.setUpdatedOn(Instant.now());
        List<UserResponse> response = userMapper.userListToResponse(List.of(user, user2));

        Assertions.assertEquals(2, response.size());
    }
}
