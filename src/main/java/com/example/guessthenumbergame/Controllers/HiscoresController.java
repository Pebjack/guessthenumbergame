package com.example.guessthenumbergame.Controllers;

import com.example.guessthenumbergame.Services.HiscoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/hiscores")
public class HiscoresController {

    private final HiscoresService hiscoresService;

    @Autowired
    public HiscoresController(HiscoresService hiscoresService) {
        this.hiscoresService = hiscoresService;
    }


    @GetMapping
    public List<Map<String, String>> Hiscores(){
        return hiscoresService.Hiscores();
    }

}