package kk.jokesapp.ui.add;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import kk.jokesapp.annotation.DatabaseExecutor;
import kk.jokesapp.event.JokeAddedEvent;
import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.Presenter;

public class NewJokePresenter extends Presenter<NewJokeScreen> {

    @Inject
    CollectionsInteractor collectionsInteractor;

    @DatabaseExecutor
    @Inject
    Executor databaseExecutor;

    @Inject
    public NewJokePresenter() {
    }

    @Override
    public void attachScreen(NewJokeScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void saveNewJoke(String type, String setup, String punchline) {

        //TODO: Checks here...

        databaseExecutor.execute(new Runnable() {
            @Override
            public void run() {
                Joke dummyJoke = new Joke(0, "dummy", "Dummy setup", "Dummy punchline");    //Temporarily
                collectionsInteractor.addJoke(dummyJoke);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final JokeAddedEvent event) {
        screen.showSaveResult(true, null);
    }
}
