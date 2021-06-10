package com.example.guessthenumbergame.GuessGame;

import javax.persistence.*;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Entity
@Table
public class GuessGame {
    @Id
    @SequenceGenerator(name= "guessgame_sequence", sequenceName = "guessgame_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guessgame_sequence")
    private long sessionId;
    private int randomNumber, attemptCount, guessNumber;
    private boolean isWon;
    private Date startTime, finishTime;
    private String message;
    private Long playTime;

    /***
     * Pusty konstruktor stworzony głównie w celach testowych.
     */
    public GuessGame() {
    }

    /***
     * Konstruktor wykorzystywany przy rozpoczeciu gry.
     * Generuje on losową liczbę z zakresu 1-100.
     * Następnie ustawia licznik prób na 1 (liczba prób będzie powiększana po każdym ruchu w metodzie game.
     * Ustawia datę rozpoczecia gry.
     * Zmienna zawierająca informację o wygranej ustawiana jest na false, jako że gracz dopiero rozpoczął grę.
     * @param startTime - na podstawie parametru określana jest data rozpoczecia gry.
     */
    public GuessGame(Date startTime) {
        Random r = new Random();
        this.randomNumber = r.nextInt(100);
        this.attemptCount = 1;
        this.startTime = startTime;
        this.isWon = false;
    }

    public long getSessionId() {return sessionId;}

    public void setSessionId(Long sessionId) {this.sessionId = sessionId;}

    public int getRandomNumber() {return randomNumber;}

    public void setRandomNumber(int randomNumber) {this.randomNumber = randomNumber;}

    public int getAttemptCount() {return attemptCount;}

    public void setAttemptCount(int attemptCount) {this.attemptCount = attemptCount;}

    public int getGuessNumber() {return guessNumber;}

    public void setGuessNumber(int guessNumber) {this.guessNumber = guessNumber;}

    public Date getStartTime() {return startTime;}

    public void setStartTime(Date startTime) {this.startTime = startTime;}

    public Date getFinishTime() {return finishTime;}

    public void setFinishTime(Date finishTime) {this.finishTime = finishTime;}

    public Long getPlayTime() {return playTime;}

    public void setPlayTime(Long playTime) {this.playTime = playTime;}

    public String getMessages() {return message;}

    public boolean getIsWon() {return isWon;}

    public void setIsWon(boolean won) {isWon = won;}

    /***
     * Metoda drukująca wszystkie zmienne obiektu klasy GuessGame. Wykorzystywana głównie do cełów testowych.
     * @return - zwraca w jednym stringu wszystkie zmienne obiektu klasy GuessGame.
     */
    @Override
    public String toString() {
        return "GuessGame{" +
                "sessionId=" + sessionId +
                ", randomNumber=" + randomNumber +
                ", attemptCount=" + attemptCount +
                ", guessNumber=" + guessNumber +
                ", startTime=" + startTime +
                ", finishTime=" + finishTime +
                ", playTime=" + playTime +
                ", message=" + message +
                ", isWon=" + isWon +
                '}';
    }

    /***
     * Cały "silnik" gry.
     * Przyjmując proponowany numer od użytkownika sprawdza go z wylosowaną wczesniej liczbą.
     * Jeżeli obie liczby są równe, zmienna isWon przyjmuje wartość true, zapisywana jest data ukończenia gry, nastepnie obliczany jest czas rozgrywki.
     * W przypadku wygranej zmienna message przyjmuje wartość "Winner!".
     * W przypadku chybionej próby licznik prób zwiększa się o 1, oraz zmienna message przyjmuje odpowiednio "Too small./Too big." zależnie od tego,
     * czy podana liczba jest mniejsza czy wieksza od wylosowanej.
     * @param guessNumber
     */
    public void game(int guessNumber){
        if(!this.isWon) {
            this.guessNumber = guessNumber;
            if (guessNumber == this.randomNumber) {
                this.isWon = true;
                this.finishTime = new Date();
                this.playTime = TimeUnit.SECONDS.convert(this.finishTime.getTime() - this.startTime.getTime(), TimeUnit.MILLISECONDS);
                this.message = "Winner!";
            } else if (guessNumber < this.randomNumber) {
                this.message = "Too small.";
                this.attemptCount = this.attemptCount +1;
            } else {
                this.message = "Too big.";
                this.attemptCount = this.attemptCount +1;
            }
        }
    }
}
