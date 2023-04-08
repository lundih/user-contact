package com.lundih.usercontact.rest;

import com.lundih.usercontact.dtos.requests.UserEditRequest;
import com.lundih.usercontact.dtos.requests.UserRequest;
import com.lundih.usercontact.dtos.responses.PageResponse;
import com.lundih.usercontact.dtos.responses.UserResponse;
import com.lundih.usercontact.exceptions.DuplicateEntryException;
import com.lundih.usercontact.exceptions.InvalidInputException;
import com.lundih.usercontact.exceptions.UserNotFoundException;
import com.lundih.usercontact.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "User resource", description = "Endpoints for managing users")
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    /**
     * Creates a user
     *
     * @param request {@link com.lundih.usercontact.dtos.requests.UserRequest} with details about the user to be created
     * @return {@link com.lundih.usercontact.dtos.responses.UserResponse} with details about the created user
     * @throws DuplicateEntryException if a user with a similar combination of nationality and nationalId is found
     * @throws InvalidInputException   if the value of an input field is found to be invalid
     */
    @PostMapping("new")
    public UserResponse createUser(@RequestBody @Validated @NotNull UserRequest request) {
        return userService.createUser(request);
    }

    /**
     * Edits user details
     *
     * @param id ID of the user whose details are to be edited
     * @param request {@link com.lundih.usercontact.dtos.requests.UserEditRequest} with details to be edited
     * @return {@link com.lundih.usercontact.dtos.responses.UserResponse} with edited details
     * @throws UserNotFoundException if the user is not found
     */
    @PatchMapping("{id}")
    public UserResponse editUser(@PathVariable @Validated @NotNull Long id,
                                 @RequestBody @Validated @NotNull UserEditRequest request) {
        return userService.editUser(id, request);
    }

    /**
     * Retrieves a user by their ID
     *
     * @param id ID by which to retrieve the user
     * @return {@link com.lundih.usercontact.dtos.responses.UserResponse} with the provided ID
     */
    @GetMapping("{id}")
    public UserResponse getUserById(@PathVariable @Validated @NotNull Long id) {
        return userService.getUserById(id);
    }

    /**
     * Retrieves users from the repository based on provided parameters
     *
     * @param pageNumber  Page from which to retrieve users. The pages start from 0
     * @param pageSize    Number of users to be retrieved
     * @param sortFields  Fields to be used to sort the users. The lower the index of the field in the list, the higher
     *                    its significance in the sort
     * @param reverseSort If true, the results will be sorted in descending order
     * @return {@link com.lundih.usercontact.dtos.responses.PageResponse} of
     * {@link com.lundih.usercontact.dtos.responses.UserResponse}
     */
    @GetMapping
    public PageResponse<UserResponse> getUsers(@RequestParam @Validated @NotNull Integer pageNumber,
                                               @RequestParam @Validated @NotNull Integer pageSize,
                                               @RequestParam(required = false) @Validated List<String> sortFields,
                                               @RequestParam(required = false) @Validated Boolean reverseSort) {
        return userService.getUsers(pageNumber, pageSize, sortFields, reverseSort);
    }

    /**
     * Retrieves a list of users who match the provided query
     *
     * @param query String against which to search for matches amongst users
     * @return List of users that match the provided query
     */
    @GetMapping("search")
    public List<UserResponse> searchUsers(@RequestParam @Validated @NotNull String query) {
        return userService.searchUsers(query);
    }
}
