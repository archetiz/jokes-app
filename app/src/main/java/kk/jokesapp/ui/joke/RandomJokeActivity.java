package kk.jokesapp.ui.joke;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.collections.CollectionsActivity;

@AndroidEntryPoint
public class RandomJokeActivity extends AppCompatActivity implements RandomJokeScreen {

    @Inject
    RandomJokePresenter randomJokePresenter;

    private TextView tvRandomJokeSetup;
    private TextView tvRandomJokePunchline;
    private View rootView;

    private TabLayout tabLayout;

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

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    Intent intent = new Intent(getBaseContext(), CollectionsActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabLayout.getTabAt(0).select();
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