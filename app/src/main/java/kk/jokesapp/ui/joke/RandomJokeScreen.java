package kk.jokesapp.ui.joke;

import kk.jokesapp.model.Joke;

public interface RandomJokeScreen {
    void showRandomJoke(Joke joke);
    void showSaveResult(Boolean success);
}
