package com.example.guessthenumbergame.Controllers;

import com.example.guessthenumbergame.Services.StartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

    private final StartService startService;

    @Autowired
    public StartController(StartService startService) {
        this.startService = startService;
    }

    @GetMapping("api/start")
    public Long Start(){return startService.Start();}

}
