package kk.jokesapp.ui.joke;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;

@AndroidEntryPoint
public class RandomJokeActivity extends AppCompatActivity implements RandomJokeScreen {

    @Inject
    RandomJokePresenter randomJokePresenter;

    private TextView tvRandomJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_joke);

        tvRandomJoke = findViewById(R.id.tvRandomJoke);

        Button saveJoke = findViewById(R.id.bSaveJoke);
        saveJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomJokePresenter.saveCurrentJoke();
            }
        });

        randomJokePresenter.showRandomJoke();
    }

    @Override
    protected void onStart() {
        super.onStart();
        randomJokePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        randomJokePresenter.detachScreen();
    }

    @Override
    public void showRandomJoke(String setup, String punchline) {
        //TODO
    }

    @Override
    public void showSaveResult(Boolean success) {
        //TODO
    }
}