package bisan.internship.devtrack.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Server is running", HttpStatus.OK);
    }

}
/*

1- JPA Entity
2- Repository
3- dto and mapper
4- service interface, and then service class
5- Controller

*/