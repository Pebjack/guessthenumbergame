package com.example.guessthenumbergame.Services;

import com.example.guessthenumbergame.GuessGame.GuessGame;
import com.example.guessthenumbergame.GuessGame.GuessGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class GuessService {

    /***
     * Serwis GuessService korzysta z bazy danych GuesGameRepository. Konieczne jest wobec tego zainicjowanie obiektu repozytorium.
     */
    private final GuessGameRepository guessGameRepository;

    @Autowired
    public GuessService(GuessGameRepository guessGameRepository) {
        this.guessGameRepository = guessGameRepository;
    }

    /***
     * Metoda Guess odpowiada za uruchomienie gry za parametry przyjmując numer sesji wygenerowany wcześniej poprzez serwis StartService, oraz zgadywany numer
     * przekazywany do serwisu GuessGame razem z requestem.
     * Do nowego obiektu klasy GuessGame przypisujemy obiekt z bazy danych.
     * W celu późniejszego zwrócenia numeru sesji, liczby prób i wiadomości tworzona jest HashMapa.
     * Następnie wywoływana jest metoda game, która porównuje liczby.
     * Gra niezaleznie od wyniku zapisywana jest do bazy danych.
     * Nastepnie w celu zwrócenia wartości w pliku JSON, poszczególne zmienne obiektu GuessGame dodawane są do HashMapy.
     * @param sessionId - metoda przyjmuje numer sesji, musi to być numer sesji zgodny z wyenerowanym przez serwis StartService.
     * @param guessNumber - zgadywany numer, przekazywany do metody poprzez plik JSON.
     * @return - zwraca zmapowane wartości pól.
     */
    public Map<String, String> Guess(long sessionId, Integer guessNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        GuessGame game = guessGameRepository.getById(sessionId);
        game.game(guessNumber);
        guessGameRepository.save(game);
        hashMap.put("SessionId", game.getSessionId()+"");
        hashMap.put("Liczba prób: ", game.getAttemptCount()+"");
        hashMap.put("Komunikat: ", game.getMessages()+"");
        return hashMap;
    }
}
