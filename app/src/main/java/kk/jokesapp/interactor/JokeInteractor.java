package kk.jokesapp.interactor;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import kk.jokesapp.event.GetRandomJokeEvent;
import kk.jokesapp.model.Joke;
import kk.jokesapp.model.JokeResult;
import kk.jokesapp.network.JokesApi;
import retrofit2.Call;
import retrofit2.Response;

public class JokeInteractor {

    private static final String TAG = "JokesInteractor";

    @Inject
    JokesApi jokesApi;

    @Inject
    public JokeInteractor() {
    }

    public void getRandomJoke() {
        GetRandomJokeEvent event = new GetRandomJokeEvent();
        try {
            Call<JokeResult> jokeQueryCall = jokesApi.getRandomJoke();
            Response<JokeResult> response = jokeQueryCall.execute();
            if(response.code() != 200) {
                throw new Exception("GetRandomJoke result is not 200.");
            }
            JokeResult result = response.body();
            event.setJoke(new Joke(result.getId(), result.getType(), result.getSetup(), result.getPunchline()));
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            Log.e(TAG, "An error occurred while getting a random joke.", e);
            event.setJoke(new Joke(0, "error", "An error occurred while getting a random joke.", ""));
            EventBus.getDefault().post(event);
        }
    }
}
