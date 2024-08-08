package bisan.internship.devtrack.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ResponseStatus(HttpStatus.OK)
public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Server is running";
    }

}
/*

1- JPA Entity
2- Repository
3- dto and mapper
4- service interface, and then service class
5- Controller

*/