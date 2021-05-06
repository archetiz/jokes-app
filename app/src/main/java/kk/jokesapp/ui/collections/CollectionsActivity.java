package kk.jokesapp.ui.collections;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.add.NewJokeActivity;
import kk.jokesapp.ui.joke.RandomJokeActivity;

@AndroidEntryPoint
public class CollectionsActivity extends AppCompatActivity implements CollectionsScreen {

    @Inject
    CollectionsPresenter collectionsPresenter;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    Intent intent = new Intent(getBaseContext(), RandomJokeActivity.class);
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

        ImageButton bAddNewJoke = findViewById(R.id.bAddNewJoke);
        bAddNewJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), NewJokeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        tabLayout.getTabAt(1).select();
        collectionsPresenter.attachScreen(this);
        collectionsPresenter.listCollections();
    }

    @Override
    protected void onStop() {
        super.onStop();
        collectionsPresenter.detachScreen();
    }

    @Override
    public void showCollectionsList(List<Joke> jokes) {
        //TODO
    }

    @Override
    public void showJokeDetails(Joke joke) {
        //TODO
    }
}