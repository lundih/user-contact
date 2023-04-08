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
import com.lundih.usercontact.exceptions.InvalidInputException;
import com.lundih.usercontact.exceptions.UserNotFoundException;
import com.lundih.usercontact.mappers.UserMapper;
import com.lundih.usercontact.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.*;

import static com.lundih.usercontact.utils.Helpers.propertyValid;

/**
 * Service to process {@link com.lundih.usercontact.entities.User} logic
 *
 * @author  lundih
 * @since 0.0.1
 */
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Creates a user
     *
     * @param request {@link com.lundih.usercontact.dtos.requests.UserRequest} with details about the user to be created
     * @return {@link com.lundih.usercontact.dtos.responses.UserResponse} with details about the created user
     * @throws DuplicateEntryException if a user with a similar combination of nationality and nationalId is found
     * @throws InvalidInputException if the value of an input field is found to be invalid
     */
    public UserResponse createUser(@NotNull UserRequest request) throws DuplicateEntryException, InvalidInputException {
        User user = userMapper.requestToUser(request);
        if (userRepository.existsByNationalIdAndNationality(user.getNationalId(), user.getNationality())) {
            throw new DuplicateEntryException("The combination of the nationality and national ID exists in the system");
        }
        user.validate();
        user = userRepository.save(user);
        logger.info(String.format("User id %s was created", user.getId()));

        return userMapper.userToResponse(user);
    }

    /**
     * Edits user details
     *
     * @param id ID of the user whose details are to be edited
     * @param request {@link com.lundih.usercontact.dtos.requests.UserEditRequest} with details to be edited
     * @return {@link com.lundih.usercontact.dtos.responses.UserResponse} with edited details
     * @throws UserNotFoundException if the user is not found
     */
    public UserResponse editUser(@NotNull Long id, @NotNull UserEditRequest request) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException(id);
        User user = optionalUser.get();
        String log = "";
        String detail = " %s %s edited to %s";
        if (propertyValid(request.getFirstName())) {
            log += String.format(detail, "firstName", user.getFirstName(), request.getFirstName());
            user.setFirstName(request.getFirstName());
        }
        if (propertyValid(request.getLastName())) {
            log += String.format(detail, "lastName", user.getLastName(), request.getLastName());
            user.setLastName(request.getLastName());
        }
        if (propertyValid(request.getCountry())) {
            log += String.format(detail, "country", user.getCountry(), request.getCountry());
            user.setCountry(request.getCountry());
        }
        if (propertyValid(request.getNationality())) {
            log += String.format(detail, "nationality", user.getNationality(), request.getNationality());
            user.setNationality(request.getNationality());
        }
        if (propertyValid(request.getBirthDay())) {
            log += String.format(detail, "birthDay", user.getBirthDay(), request.getBirthDay());
            user.setBirthDay(request.getBirthDay());
        }
        if (propertyValid(request.getGender())) {
            log += String.format(detail, "gender", user.getGender(), request.getGender());
            user.setGender(request.getGender());
        }
        if (propertyValid(request.getNationalId())) {
            log += String.format(detail, "nationalId", user.getNationalId(), request.getNationalId());
            user.setNationalId(request.getNationalId());
        }
        user.validate();
        user = userRepository.save(user);
        if (!log.equals("")) logger.info(String.format("User id %d %s", id, log));

        return userMapper.userToResponse(user);
    }

    /**
     * Retrieves a user by their ID
     *
     * @param id ID by which to retrieve the user
     * @return {@link com.lundih.usercontact.dtos.responses.UserResponse} with the provided ID
     */
    public UserResponse getUserById(@NotNull Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException(id);

        return userMapper.userToResponse(optionalUser.get());
    }

    /**
     * Retrieves users from the repository based on provided parameters
     *
     * @param pageNumber Page from which to retrieve users. The pages start from 0
     * @param pageSize Number of users to be retrieved
     * @param sortFields Fields to be used to sort the users. The lower the index of the field in the list, the higher
     * its significance in the sort
     * @param reverseSort If true, the results will be sorted in descending order
     * @return {@link com.lundih.usercontact.dtos.responses.PageResponse} of
     * {@link com.lundih.usercontact.dtos.responses.UserResponse}
     */
    public PageResponse<UserResponse> getUsers(@NotNull Integer pageNumber,
                                               @NotNull Integer pageSize,
                                               List<String> sortFields,
                                               Boolean reverseSort) {
        ArrayList<String> sortList = new ArrayList<>();
        sortList.add("id");
        Sort.Direction sortDirection = Sort.Direction.ASC;
        if (sortFields != null) {
            if (!sortFields.isEmpty()) sortList = new ArrayList<>(sortFields);
        }
        if (reverseSort != null) {
            if (reverseSort) sortDirection = Sort.Direction.DESC;
        }
        Page<User> page = userRepository.findBy(
                PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortList.toArray(new String[0]))));

        return new PageResponse<>(userMapper.userListToResponse(page.getContent()), page.getTotalElements());
    }

    /**
     * Retrieves a list of users who match the provided query
     *
     * @param query String against which to search for matches amongst users
     * @return List of users that match the provided query
     */
    public List<UserResponse> searchUsers(@NotNull String query) {
        User user = new User();
        user.setFirstName(query);
        user.setLastName(query);
        user.setNationalId(query);
        Example<User> example = Example.of(user, ExampleMatcher.matchingAny()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnoreNullValues());

        return userMapper.userListToResponse(userRepository.findAll(example,
                PageRequest.ofSize(20).withSort(Sort.by("firstName").ascending())));
    }

    /**
     * Gets the list of nationalities
     *
     * @return Hashmap of nationalities with the name for input as the key and the name for the output as the value
     */
    public TreeMap<Nationality, String> getNationalities() {
        return getValues(Nationality.class);
    }

    /**
     * Gets the list of countries
     *
     * @return Hashmap of countries with the name for input as the key and the name for the output as the value
     */
    public TreeMap<Country, String> getCountries() {
        return getValues(Country.class);
    }

    /**
     * Gets the list of genders
     *
     * @return Hashmap of genders with the name for input as the key and the name for the output as the value
     */
    public TreeMap<Gender, String> getGenders() {
        return getValues(Gender.class);
    }

    /**
     * Gets the list of enum values
     *
     * @return Hashmap of nationalities with the name for input as the key and the name for the output as the value
     */
    public <T extends Enum<T>> TreeMap<T, String> getValues(Class<T> elementType) {
        // Use treemap so values are sorted (hashmaps are not guaranteed to sort items)
        TreeMap<T, String> values = new TreeMap<>();
        EnumSet.allOf(elementType).forEach(value -> {
            values.put(value, value.toString());
        });

        return values;
    }


}
