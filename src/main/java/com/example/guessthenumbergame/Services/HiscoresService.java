package com.example.guessthenumbergame.Services;

import com.example.guessthenumbergame.GuessGame.GuessGame;
import com.example.guessthenumbergame.GuessGame.GuessGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HiscoresService {
    /***
     * Serwis HiscoresService korzysta z bazy danych GuesGameRepository. Konieczne jest wobec tego zainicjowanie obiektu repozytorium.
     */
    private final GuessGameRepository guessGameRepository;

    @Autowired
    public HiscoresService(GuessGameRepository guessGameRepository) {
        this.guessGameRepository = guessGameRepository;
    }

    /***
     * Metoda Hiscores pobiera listę 10 najlepszych gier korzystając z zapytania SQL zdefiniowanego w klasie GuessGameRepository.
     * W tym celu tworzona jest nowa lista obiektów klasy Map, oraz lista 10 najlepszych gier pobrana z bazy danych.
     * Tworzona jest również nowa HashMapa która będzie pobierała wartości zmiennych gier z azy danych.
     * Pętla przegląda wszystkie gry z listy gameList, pobierając z kazdej gry potrzebne dane i zapisuje je do HashMapy.
     * Na koniec każdej iteracji pętli Haszmapa dodawana jest do listy guessGameList a nastepnie jest zerowana aby móc zapisać kolejną grę.
     * Zwracana lista guessGameList zawiera czas gry, ilośc ruchów oraz nr sesji 10 najlepszych gier.
     * @return - zwraca listę maksymalnie 10 najlepszych wyników pobranych z bazy danych, w przypadku gdy liczba
     * rozegranych gier jest mniejsza, zwróci odpowiednio ranking wszystkich gier.
     */
    public List<Map<String , String>> Hiscores(){
        List<Map<String, String >> guessGameList = new ArrayList<>();
        List<GuessGame> gameList = guessGameRepository.top10();
        HashMap<String, String> map = new HashMap<>();
        for (GuessGame game : gameList) {
            map.put("Czas gry: ", game.getPlayTime() +" sekund");
            map.put("Ilość Ruchów: ", game.getAttemptCount()+"");
            map.put("Nr sesji: ", game.getSessionId()+"");
            guessGameList.add(map);
            map = new HashMap<>();
        }
        return guessGameList;
    }

}
