package com.lundih.usercontact.mappers;

import com.lundih.usercontact.dtos.requests.UserRequest;
import com.lundih.usercontact.dtos.responses.UserResponse;
import com.lundih.usercontact.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * Maps between {@link com.lundih.usercontact.entities.User} and its DTOs
 * <p>
 * The interface will use Mapstruct at compile time to generate a mapper class that will be used for the actual mapping
 *
 * @author lundih
 * @since 0.0.1
 */
@Mapper
public interface UserMapper {
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "createdOn", ignore = true),
        @Mapping(target = "updatedOn", ignore = true)})
    User requestToUser(UserRequest request);

    @Mappings({
        @Mapping(target = "country", expression = "java(user.getCountry().toString())"),
        @Mapping(target = "nationality", expression = "java(user.getNationality().toString())"),
        @Mapping(target = "gender", expression = "java(user.getGender().toString())")})
    UserResponse userToResponse(User user);

    List<UserResponse> userListToResponse(List<User> users);
}
