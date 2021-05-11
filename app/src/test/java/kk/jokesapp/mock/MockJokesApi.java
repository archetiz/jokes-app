package kk.jokesapp.mock;

import java.io.IOException;

import kk.jokesapp.model.JokeResult;
import kk.jokesapp.network.JokesApi;
import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MockJokesApi implements JokesApi {

    @Override
    public Call<JokeResult> getRandomJoke() {
        final JokeResult jokeResult = new JokeResult();
        jokeResult.setId(42);
        jokeResult.setType("test");
        jokeResult.setSetup("TestSetup");
        jokeResult.setPunchline("TestPunchline");

        Call<JokeResult> jokeResultCall = new Call<JokeResult>() {
            @Override
            public Response<JokeResult> execute() throws IOException {
                return Response.success(jokeResult);
            }

            @Override
            public void enqueue(Callback<JokeResult> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<JokeResult> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }

            @Override
            public Timeout timeout() {
                return null;
            }
        };
        return jokeResultCall;
    }
}
