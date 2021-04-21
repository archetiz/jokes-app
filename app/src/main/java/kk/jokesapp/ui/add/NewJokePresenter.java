package kk.jokesapp.ui.add;

import javax.inject.Inject;

import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.Presenter;

public class NewJokePresenter extends Presenter<NewJokeScreen> {

    @Inject
    CollectionsInteractor collectionsInteractor;

    @Inject
    public NewJokePresenter() {
    }

    @Override
    public void attachScreen(NewJokeScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void saveNewJoke(String type, String setup, String punchline) {
        Joke dummyJoke = new Joke(0, "dummy", "Dummy setup", "Dummy punchline");    //Temporarily
        collectionsInteractor.addJoke(dummyJoke);
        screen.showSaveResult(true, null);
    }
}
