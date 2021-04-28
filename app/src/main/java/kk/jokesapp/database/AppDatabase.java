package kk.jokesapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import kk.jokesapp.database.data.JokeDao;
import kk.jokesapp.database.data.JokeEntity;

@Database(entities = {JokeEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract JokeDao jokeDao();
}
