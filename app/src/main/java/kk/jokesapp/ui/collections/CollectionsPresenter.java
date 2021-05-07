package kk.jokesapp.ui.collections;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import kk.jokesapp.annotation.DatabaseExecutor;
import kk.jokesapp.event.GetJokesEvent;
import kk.jokesapp.event.JokeDeletedEvent;
import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.ui.Presenter;

public class CollectionsPresenter extends Presenter<CollectionsScreen> {

    @Inject
    CollectionsInteractor collectionsInteractor;

    @DatabaseExecutor
    @Inject
    Executor databaseExecutor;

    @Inject
    public CollectionsPresenter() {
    }

    @Override
    public void attachScreen(CollectionsScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    private void refreshList() {
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                collectionsInteractor.getJokes();
            }
        });
    }

    public void listCollections() {
        refreshList();
    }

    public void removeFromCollection(int id) {
        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                collectionsInteractor.removeJoke(id);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetJokesEvent event) {
        screen.showCollectionsList(event.getJokes());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final JokeDeletedEvent event) {
        refreshList();
    }
}
