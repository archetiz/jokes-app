package kk.jokesapp.ui.collections;

import javax.inject.Inject;

import kk.jokesapp.ui.Presenter;

public class CollectionsPresenter extends Presenter<CollectionsScreen> {

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

    public void listCollections() {
        //TODO
    }

    public void removeFromCollection(int id) {
        //TODO
        //...
        //showCollectionsList(...)
    }

    public void showJokeDetails(int id) {
        //TODO
    }
}
