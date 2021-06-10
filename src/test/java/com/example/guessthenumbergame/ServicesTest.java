package com.example.guessthenumbergame;

import com.example.guessthenumbergame.GuessGame.GuessGameRepository;
import com.example.guessthenumbergame.Services.StartService;
import com.sun.istack.NotNull;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class ServicesTest {

   @Autowired
   private GuessGameRepository guessGameRepository;
   @Autowired
   private StartService startService;

    /***
     * Test sprawdza czy przy korzystaniu z serwisu Start zapisywany jest nowy obiekt w bazie danych.
     */
    @Test
    public void shouldInitiateNewGameObjectOnStart(){
        //When
        Long response = startService.Start();
        //Then
        Assert.assertNotNull(guessGameRepository.getById(response));
    }

    /***
     * Test sprawdza czy przy korzystaniu z serwisu Start nadawany jest numer sesji.
     * W tym celu porównuje nadany numer sesji z przewidzianym.
     */
    @Test
    public void shouldInitiateSessionIdOnStart(){
        //Given
        Long response = startService.Start();
        //When
        Long l = guessGameRepository.getById(response).getSessionId();
        //Then
        Assert.assertNotNull(l);
    }

    /***
     * Test sprawdza czy przy korzystaniu z serwisu Start nadawana jest data rozpoczecia gry.
     */
    @Test
    public void shouldInitiateStartDateOnStart(){
        //Given
        Long response = startService.Start();
        //When
        Date d = guessGameRepository.getById(response).getStartTime();
        //Then
        Assert.assertNotNull(d);
    }

}
