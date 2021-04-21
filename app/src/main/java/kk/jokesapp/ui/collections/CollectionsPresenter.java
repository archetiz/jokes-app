package kk.jokesapp.ui.collections;

import java.util.List;

import javax.inject.Inject;

import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.Presenter;

public class CollectionsPresenter extends Presenter<CollectionsScreen> {

    @Inject
    CollectionsInteractor collectionsInteractor;

    @Inject
    public CollectionsPresenter() {
    }

    @Override
    public void attachScreen(CollectionsScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    private void refreshList() {
        List<Joke> jokes = collectionsInteractor.getJokes();
        screen.showCollectionsList(jokes);
    }

    public void listCollections() {
        refreshList();
    }

    public void removeFromCollection(int id) {
        collectionsInteractor.removeJoke(id);
        refreshList();
    }

    public void showJokeDetails(int id) {
        Joke joke = collectionsInteractor.getJoke(id);
        screen.showJokeDetails(joke);
    }
}
