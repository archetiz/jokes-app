package kk.jokesapp.ui.collections;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.add.NewJokeActivity;

@AndroidEntryPoint
public class CollectionsFragment extends Fragment implements CollectionsScreen {

    @Inject
    CollectionsPresenter collectionsPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_collections, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton bAddNewJoke = view.findViewById(R.id.bAddNewJoke);
        bAddNewJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewJokeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        collectionsPresenter.attachScreen(this);
        collectionsPresenter.listCollections();
    }

    @Override
    public void onStop() {
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