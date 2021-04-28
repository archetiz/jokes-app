package kk.jokesapp.database.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import kk.jokesapp.database.DatabaseConfig;
import kk.jokesapp.model.Joke;

@Entity(tableName = DatabaseConfig.JOKES_TABLE_NAME)
public class JokeEntity {

    @PrimaryKey(autoGenerate = true)
    private int jokeId;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "setup")
    private String setup;

    @ColumnInfo(name = "punchline")
    private String punchline;

    public JokeEntity() {
    }

    public JokeEntity(Joke joke) {
        this.type = joke.getType();
        this.setup = joke.getSetup();
        this.punchline = joke.getPunchline();
    }

    public int getJokeId() {
        return jokeId;
    }

    public void setJokeId(int jokeId) {
        this.jokeId = jokeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }
}
