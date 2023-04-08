package com.lundih.usercontact.rest;

import com.lundih.usercontact.enums.Nationality;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller to handle requests to check if the server is up
 *
 * @author lundih
 * @since 0.0.1
 *
 */
@Tag(name = "Test resource", description = "Test Endpoints")
@RestController
@RequestMapping("api/v1/tests")
public class TestResource {
    /**
     * Endpoint that returns a message. This can be used for smoke test purposes
     *
     * @return String that indicates the server is running
     */
    @GetMapping
    String testApp() {
        return "The server is up!";
    }

    @GetMapping("/nationalities")
    Nationality[] getNationalities() {
        return Nationality.values();
    }
}
