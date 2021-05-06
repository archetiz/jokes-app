package kk.jokesapp.ui.joke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;

@AndroidEntryPoint
public class RandomJokeActivity extends AppCompatActivity implements RandomJokeScreen {

    @Inject
    RandomJokePresenter randomJokePresenter;

    private TextView tvRandomJokeSetup;
    private TextView tvRandomJokePunchline;
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_joke);

        rootView = findViewById(android.R.id.content);

        tvRandomJokeSetup = findViewById(R.id.tvRandomJokeSetup);
        tvRandomJokePunchline = findViewById(R.id.tvRandomJokePunchline);

        Button saveJoke = findViewById(R.id.bSaveJoke);
        saveJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomJokePresenter.saveCurrentJoke();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        randomJokePresenter.attachScreen(this);
        randomJokePresenter.showRandomJoke();
    }

    @Override
    protected void onStop() {
        super.onStop();
        randomJokePresenter.detachScreen();
    }

    @Override
    public void showRandomJoke(Joke joke) {
        tvRandomJokeSetup.setText(joke.getSetup());
        tvRandomJokePunchline.setText(joke.getPunchline());
    }

    @Override
    public void showSaveResult(Boolean success) {
        Snackbar.make(rootView, R.string.succesful_save, Snackbar.LENGTH_LONG)
                .setBackgroundTint(getResources().getColor(R.color.success))
                .show();
    }
}