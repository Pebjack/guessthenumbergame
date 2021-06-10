package com.example.guessthenumbergame;

import com.example.guessthenumbergame.GuessGame.GuessGame;
import com.example.guessthenumbergame.GuessGame.GuessGameRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class GuessGameRepositoryTest {

    /***
     * Definiujemy obiekt Random na potrzeby testów.
     */
    Random random = new Random();

    @Autowired
    private GuessGameRepository guessGameRepository;

    /***
     * Test sprawdza czy rozgrywane gry są poprawnie dodawane do bazy danych.
     * Tworzona jest nowa gra o aktualnej dacie rozpoczęcia.
     * Następnie pętla for generuje losową liczbę blednych zagrań.
     * Po zakończeniu pętli rozgrywana jest jescze jedna gra, tym razem z poprawnym wynikiem.
     * Nastepuje zapisanie gry do bazy danych.
     * Liczba obiektów w baze danych powinna wynosić 1.
      */
    @Test
    public void shouldAddGameToRepository(){
        //Given
        GuessGame game = new GuessGame(new Date());
        for(int j = 1; j < random.nextInt(20); j++){
            game.game(game.getRandomNumber() - random.nextInt(5));
        }
        game.game(game.getRandomNumber());

        //When
        this.guessGameRepository.save(game);

        //Then
        Assert.assertEquals(guessGameRepository.count(),1,0);

    }

    /***
     * Test sprawdza czy zwracane jest 10 najlepszych wyników z bazy anych.
     * Pierwsza pętla z kazdą iteracją tworzy nową grę.
     * Druga pętla tworzy losową liczbę niepoprawnych prób gry.
     * Po zakonczeniu drugiej pętli, pierwsza przeprowadza grę z trafioną liczbą.
     * Gra jest zapisywana do bazy danych.
     * Po zakończeniu pętli pobierane jest tylko 10 najlepszych wyników z bazy.
     * Rozmiar listy pobranych obiektów powinien wynosić 10.
     */
    @Test
    public void shouldReturnTop10BestGames(){
        //Given
        for(int i = 0; i < 15; i++){
            GuessGame game = new GuessGame(new Date());
            for(int j = 1; j < random.nextInt(20); j++){
                game.game(game.getRandomNumber() - random.nextInt(5));
            }
            game.game(game.getRandomNumber());
            this.guessGameRepository.save(game);
        }

        //When
        List<GuessGame> top10 = guessGameRepository.top10();

        //Then
        Assert.assertEquals(top10.size(),10,0);
    }
}
