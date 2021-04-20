package kk.jokesapp.ui.collections;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;

@AndroidEntryPoint
public class CollectionsActivity extends AppCompatActivity implements CollectionsScreen {

    @Inject
    CollectionsPresenter collectionsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);

        collectionsPresenter.listCollections();
    }

    @Override
    protected void onStart() {
        super.onStart();
        collectionsPresenter.attachScreen(this);
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