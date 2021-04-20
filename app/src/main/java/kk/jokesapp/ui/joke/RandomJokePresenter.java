package kk.jokesapp.ui.joke;

import javax.inject.Inject;

import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.Presenter;

public class RandomJokePresenter extends Presenter<RandomJokeScreen> {

    private Joke currentJoke;

    @Inject
    public RandomJokePresenter() {
    }

    @Override
    public void attachScreen(RandomJokeScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showRandomJoke() {
        //TODO
    }

    public void saveCurrentJoke() {
        //TODO
    }
}
