package kk.jokesapp.ui.joke;

import javax.inject.Inject;

import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.interactor.JokeInteractor;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.Presenter;

public class RandomJokePresenter extends Presenter<RandomJokeScreen> {

    private Joke currentJoke;

    @Inject
    CollectionsInteractor collectionsInteractor;

    @Inject
    JokeInteractor jokeInteractor;

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
        currentJoke = jokeInteractor.getRandomJoke();
        screen.showRandomJoke(currentJoke);
    }

    public void saveCurrentJoke() {
        collectionsInteractor.addJoke(currentJoke);
        screen.showSaveResult(true);
    }
}
