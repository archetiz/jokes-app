package kk.jokesapp.network;

import kk.jokesapp.model.JokeResult;
import retrofit2.Call;
import retrofit2.http.*;

public interface JokesApi {
  /**
   * Get a random joke.
   * Returns a randomly selected joke.
   * @return Call<JokeResult>;
   */
  @GET("random_joke")
  Call<JokeResult> randomJoke();
}
