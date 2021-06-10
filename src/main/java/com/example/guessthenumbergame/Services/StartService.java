package com.example.guessthenumbergame.Services;

import com.example.guessthenumbergame.GuessGame.GuessGame;
import com.example.guessthenumbergame.GuessGame.GuessGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StartService {

    /***
     * Serwis StartService korzysta z bazy danych GuesGameRepository. Konieczne jest wobec tego zainicjowanie obiektu repozytorium.
     */
    private final GuessGameRepository guessGameRepository;

    @Autowired
    public StartService(GuessGameRepository guessGameRepository) {
        this.guessGameRepository = guessGameRepository;
    }

    /***
     * Metoda Start odpowiada za utworzenie nowej gry. W momencie wywołania generowana jest aktualna data.
     * Nastepnie data przekazywana est do konstruktora i tworzony jest nowy obiekt klasy GuessGame.
     * Obiekt zapisywany jest do bazy anych.
     * @return - zwraca numer sesji Gry pobranej z bazy danych.
     */
    public Long Start(){
        Date date = new Date();
        GuessGame game = new GuessGame(date);
        guessGameRepository.save(game);
        return guessGameRepository.getById(game.getSessionId()).getSessionId();
    }

}
