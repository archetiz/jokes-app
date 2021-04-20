package kk.jokesapp.ui.joke;

public interface RandomJokeScreen {
    void showRandomJoke(String setup, String punchline);
    void showSaveResult(Boolean success);
}
