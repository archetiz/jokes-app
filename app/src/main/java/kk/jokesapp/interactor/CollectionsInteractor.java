package kk.jokesapp.interactor;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kk.jokesapp.database.AppDatabase;
import kk.jokesapp.database.data.JokeEntity;
import kk.jokesapp.event.GetJokesEvent;
import kk.jokesapp.event.JokeAddedEvent;
import kk.jokesapp.event.JokeDeletedEvent;
import kk.jokesapp.model.Joke;

public class CollectionsInteractor {

    @Inject
    AppDatabase appDatabase;

    @Inject
    public CollectionsInteractor() {
    }

    public void getJokes() {
        List<JokeEntity> entities = appDatabase.jokeDao().getJokes();
        List<Joke> jokes = new ArrayList<>();
        for (JokeEntity entity : entities) {
           Joke joke = new Joke(entity.getJokeId(), entity.getType(), entity.getSetup(), entity.getPunchline());
           jokes.add(joke);
        }
        GetJokesEvent event = new GetJokesEvent(jokes);
        EventBus.getDefault().post(event);
    }

    public void addJoke(Joke joke) {
        appDatabase.jokeDao().insertJoke(new JokeEntity(joke));
        EventBus.getDefault().post(new JokeAddedEvent());
    }

    public void removeJoke(int id) {
        appDatabase.jokeDao().deleteJoke(id);
        EventBus.getDefault().post(new JokeDeletedEvent());
    }
}
