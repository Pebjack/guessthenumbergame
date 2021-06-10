package com.example.guessthenumbergame;

import com.example.guessthenumbergame.GuessGame.GuessGame;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class GuessGameClassTest {

    GuessGame game = new GuessGame(new Date());

    @Test
    public void shouldPrintGuessGameObject() {
        Assert.assertNotNull(game.toString());
    }

    /***
     * Test sprawdza czy przy wywołaniu metody Start tworzony jest nowy obiekt klasy GuessGame.
     * Przy tworzeniu obiektu sprawdza czy wymagane parametry odpowiadają przewidywanym wartościom.
     */
    @Test
    public void shouldCreateNewGameDataOnStart(){
        //Given
        Date date = new Date();
        //When
        GuessGame game2 = new GuessGame(date);
        //Then
        Assert.assertNotNull(game2.getStartTime());
        Assert.assertEquals(game2.getAttemptCount(), 1, 0);
        Assert.assertEquals(game2.getRandomNumber(), 0, 100);
        Assert.assertFalse(game2.getIsWon());
    }

    /***
     * test sprawdza czy metoda game poprawnie przyjmuje zgadywany numer.
     */
    @Test
    public void shouldAcceptGuessNumber(){
        //Given
        GuessGame game3 = new GuessGame(new Date());
        int guess = 15;

        //When
        game3.game(guess);

        //Then
        Assert.assertEquals(game3.getGuessNumber(),guess,0);
    }

    /***
     * Test sprawdza czy metoda game zwiększa liczbę prób przy nieudanym zgadnięciu.
     * W tym celu generowana jest gra z niepoprawną wrtością zgadnięcia.
     * Nastepnie liczba prób porównywana jest do stałej liczby 1.
     * Liczba prób powinna być wieksza od 1.
     */
    @Test
    public void shouldIncrementAttemptCounterOnMissedGuess(){
        //Given
        GuessGame game4 = new GuessGame(new Date());
        int attempt = 1;

        //When
        game4.game(game4.getRandomNumber()-1);
        boolean isBigger = game4.getAttemptCount() > attempt;

        //Then
        Assert.assertTrue(isBigger);
    }

    /***
     * Odpowiednie zaprzeczenie poprzedniego testu.
     * Test sprawdza czy przy trafieniu poprawnej liczby liczba prób zwieksza się.
     * Liczba prób powinna być równa zmiennej attempt.
     */
    @Test
    public void shouldNotIncrementAttemptCounterOnWin(){
        //Given
        GuessGame game = new GuessGame(new Date());
        int attempt = 1;

        //When
        game.game(game.getRandomNumber());

        //Then
        Assert.assertEquals(game.getAttemptCount(), attempt,0);
    }

    /**
     * Test sprawdza czy przy poprawnym trafieniu liczby ustalany jest czas zakończenia gry.
     * Przy zakończeniu testu zmienna z czasem zakończenia gry powinna byc zdefiniowana.
     */
    @Test
    public void shouldSetFinishTimeOnWin(){
        //When
        game.game(game.getRandomNumber());

        //Then
        Assert.assertNotNull(game.getFinishTime());
    }

    /***
     * Negacja poprzedniego testu.
     * Test sprawdza czy przy niepoprawnym zgadnięciu ustalany jest czas zakończenia gry.
     * Przy zakończeniu testu zmienna z czasem zakończenia nie powinna być zdefiniowana.
     */
    @Test
    public void shouldNotSetFinishTimeOnMissedGuess(){
        //When
        game.game(game.getRandomNumber()-1);

        //Then
        Assert.assertNull(game.getFinishTime());
    }

    /***
     * Test sprawza czy przy wygranej obliczany jest łączny czas gry.
     * Na potrzeby testu definiujemy czas rozpoczęcia oraz zakończenia gry.
     * Po wywołaniu wygranej gry zmienna łącznego czasu gry powinna byc zdefiniowana.
     */
    @Test
    public void shouldCalculateGameTimeOnWinning(){
        //Given
        game.setStartTime(new Date());
        game.setFinishTime(new Date(2023));

        //When
        game.game(game.getRandomNumber());

        //Then
        Assert.assertNotNull(game.getPlayTime());
    }

    /***
     * Test sprawdza czy w przypadku wygranej gry zmienna message przyjmuje odpowiednią wartość.
     */
    @Test
    public void shouldSetMessageOnWin(){
        //When
        game.game(game.getRandomNumber());

        //Then
        Assert.assertNotNull(game.getMessages());
        Assert.assertEquals(game.getMessages(),"Winner!",game.getMessages());
    }

    /***
     * Test sprawdza czy w przypadku podania przez użytkownika liczby mniejszej od wylosowanej zmienna message przyjmuje odpowiednią wartość.
     */
    @Test
    public void shouldSetMessageOnTooSmallGuess(){
        //When
        game.game(game.getRandomNumber()-1);

        //Then
        Assert.assertNotNull(game.getMessages());
        Assert.assertEquals(game.getMessages(),"Too small.",game.getMessages());
    }

    /***
     * Test sprawdza czy w przypadku podania przez użytkownika liczby wiekszej od wylosowanej zmienna message przyjmuje odpowiednią wartość.
     */
    @Test
    public void shouldSetMessageOnTooBigGuess(){
        //When
        game.game(game.getRandomNumber()+1);

        //Then
        Assert.assertNotNull(game.getMessages());
        Assert.assertEquals(game.getMessages(),"Too big.",game.getMessages());
    }

    /***
     * Test sprawza czy przy nietrafionym typie obliczany jest łączny czas gry.
     * Na potrzeby testu tworzymy nowy obiekt GuessGame jako parametr podając aktualną datę jako datę rozpoczecia gry.
     * Następnie przeprowadzana jest gra z niepoprawnym typem.
     * Zmienna łącznego czasu gry powinna byc niezdefiniowana.
     */
    @Test
    public void shouldNotCalculateGameTimeOnMissedGuess(){
        //Given
        GuessGame game5 = new GuessGame(new Date());

        //When
        game5.game(game5.getRandomNumber()-1);

        //Then
        Assert.assertNull(game5.getPlayTime());
    }


}
