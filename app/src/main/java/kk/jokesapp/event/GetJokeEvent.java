package kk.jokesapp.event;

import kk.jokesapp.model.Joke;

public class GetJokeEvent {

    private Joke joke;

    public GetJokeEvent() {
    }

    public GetJokeEvent(Joke joke) {
        this.joke = joke;
    }

    public Joke getJoke() {
        return joke;
    }

    public void setJoke(Joke joke) {
        this.joke = joke;
    }
}
