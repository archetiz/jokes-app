package kk.jokesapp.ui.joke;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import kk.jokesapp.annotation.NetworkExecutor;
import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.interactor.JokeInteractor;
import kk.jokesapp.event.GetRandomJokeEvent;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.Presenter;

public class RandomJokePresenter extends Presenter<RandomJokeScreen> {

    private Joke currentJoke;

    @Inject
    CollectionsInteractor collectionsInteractor;

    @Inject
    JokeInteractor jokeInteractor;

    @NetworkExecutor
    @Inject
    Executor networkExecutor;

    @Inject
    public RandomJokePresenter() {
    }

    @Override
    public void attachScreen(RandomJokeScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void saveCurrentJoke() {
        collectionsInteractor.addJoke(currentJoke);
        screen.showSaveResult(true);
    }

    public void showRandomJoke() {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                jokeInteractor.getRandomJoke();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetRandomJokeEvent event) {
        currentJoke = event.getJoke();
        if(screen != null) {
            screen.showRandomJoke(currentJoke);
        }
    }
}
