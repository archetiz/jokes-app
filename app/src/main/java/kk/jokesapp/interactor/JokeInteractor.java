package kk.jokesapp.interactor;

import javax.inject.Inject;

import kk.jokesapp.model.Joke;

public class JokeInteractor {

    @Inject
    public JokeInteractor() {
    }

    public Joke getRandomJoke() {
        Joke dummyJoke = new Joke(0, "dummy", "Dummy setup", "Dummy punchline");    //Temporarily
        return dummyJoke;
    }
}
