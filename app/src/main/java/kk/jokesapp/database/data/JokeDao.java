package kk.jokesapp.database.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JokeDao {

    @Query("SELECT * FROM jokes")
    List<JokeEntity> getJokes();

    @Query("SELECT * FROM jokes WHERE jokeId = :id")
    JokeEntity getJoke(int id);

    @Insert
    void insertJoke(JokeEntity joke);

    @Query("DELETE FROM jokes WHERE jokeId = :id")
    void deleteJoke(int id);
}
