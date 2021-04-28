package kk.jokesapp.event;

import java.util.List;

import kk.jokesapp.model.Joke;

public class GetJokesEvent {

    private List<Joke> jokes;

    public GetJokesEvent() {
    }

    public GetJokesEvent(List<Joke> jokes) {
        this.jokes = jokes;
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(List<Joke> jokes) {
        this.jokes = jokes;
    }
}
