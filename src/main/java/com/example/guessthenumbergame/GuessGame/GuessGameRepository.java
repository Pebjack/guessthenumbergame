package com.example.guessthenumbergame.GuessGame;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuessGameRepository extends JpaRepository<GuessGame, Long> {

    /***
     * Zapytanie pobiera 10 gier z bazy danych i sortuje je rosnąco według liczby prób.
     * @return - zwraca listę obiektów GuessGame
     */
    @Query(value = "SELECT * FROM guess_game u where is_won = true ORDER BY attempt_count, play_time LIMIT 10", nativeQuery = true)
    public List<GuessGame> top10();

}
