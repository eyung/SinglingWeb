package com.singling.swbackend;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class MainController {

    List<TransformationManager.Instruction> instructions = new ArrayList<>();

    Set<String> passingWords = new HashSet<String>();

    // Create and init producer
    Producer producer = new Producer();

    // Create Composer
    Composer composer;

    @GetMapping("/api")
    public String getTest() {
        return ("test");
    }

    @PostMapping("/text")
    public void play(@RequestBody String input) {

        // Init Composer
        composer = new Composer
                .ComposerBuilder()
                .setInstrument("Piano")
                .setNoteLength(0.50)
                .setOctave(3)
                .setTempo(350.00)
                .setFrequency(200.00)
                .setRestLength(0.50)
                .setRestLengthLineBreak(1.00)
                .wantWord(true)
                .withOperation("LEXNAMEFREQ")
                .withOrdering(0)
                .useTransformations(instructions)
                .excludeWords(passingWords)
                .build();

        // Process user input text
        composer.processString(input);

        // Init Producer using pattern created by Composer
        producer.setPlayer();

        // Pass created sound pattern to producer
        producer.setPattern(composer.getPattern());

        // Start player
        producer.doStartPlayer(0.50);

        //return "USER TEXT: " + input;
    }

    @PostMapping("/togglepause")
    public void togglePause() {
        producer.doPause();
    }
}
