package com.example.guessthenumbergame.Controllers;

import com.example.guessthenumbergame.Services.GuessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "api/guess/{sessionId}")
public class GuessController {

    private final GuessService guessService;

    @Autowired
    public GuessController(GuessService guessService) {this.guessService = guessService;}


    @GetMapping
    public Map<String, String> Guess(@PathVariable("sessionId") long sessionId, Integer guessNumber){
       return guessService.Guess(sessionId,guessNumber);
    }

}