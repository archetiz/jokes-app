package kk.jokesapp.interactor.event;

import kk.jokesapp.model.Joke;

public class GetRandomJokeEvent {

    private Joke joke;

    public GetRandomJokeEvent() {
    }

    public GetRandomJokeEvent(Joke joke) {
        this.joke = joke;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }
}
