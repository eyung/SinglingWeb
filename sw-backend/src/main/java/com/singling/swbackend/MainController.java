package com.singling.swbackend;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    String inputText;

    @GetMapping("/api")
    public String getTest() {
        return ("test");
    }

    @PostMapping("/text")
    public String doSomething(@RequestBody String input) {
        this.inputText = input;
        System.out.println(inputText);
        return "USER TEXT: " + inputText;
    }
}
