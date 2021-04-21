package kk.jokesapp.interactor;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kk.jokesapp.model.Joke;

public class CollectionsInteractor {

    @Inject
    public CollectionsInteractor() {

    }

    public List<Joke> getJokes() {
        List<Joke> jokes = new ArrayList<>();   //Temporarily
        jokes.add(getJoke((0)));
        return jokes;
    }

    public Joke getJoke(int id) {
        Joke dummyJoke = new Joke(0, "dummy", "Dummy setup", "Dummy punchline");    //Temporarily
        return dummyJoke;
    }

    public void addJoke(Joke joke) {
        //TODO
    }

    public void removeJoke(int id) {
        //TODO
    }
}
