package kk.jokesapp.ui.add;

import javax.inject.Inject;

import kk.jokesapp.ui.Presenter;

public class NewJokePresenter extends Presenter<NewJokeScreen> {

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
        //TODO
    }
}
