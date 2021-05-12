package kk.jokesapp.ui.add;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import kk.jokesapp.R;
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

        List<Integer> checkResults = doChecksBeforeSave(type, setup, punchline);

        if(checkResults.size() > 0) {
            screen.showSaveResult(false, checkResults);
        }
        else {
            databaseExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    Joke newJoke = new Joke(0, type, setup, punchline);
                    collectionsInteractor.addJoke(newJoke);
                }
            });
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final JokeAddedEvent event) {
        screen.showSaveResult(true, null);
    }

    private List<Integer> doChecksBeforeSave(String type, String setup, String punchline) {
        List<Integer> errors = new ArrayList<>();
        if(type.isEmpty())
            errors.add(R.string.error_joke_category_empty);
        if(setup.isEmpty())
            errors.add(R.string.error_joke_setup_empty);
        if(punchline.isEmpty())
            errors.add(R.string.error_joke_punchline_empty);
        return errors;
    }
}
