package kk.jokesapp.ui.collections;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.add.NewJokeActivity;

@AndroidEntryPoint
public class CollectionsFragment extends Fragment implements CollectionsScreen, CollectionsListAdapter.ItemClickListener {

    @Inject
    CollectionsPresenter collectionsPresenter;

    CollectionsListAdapter collectionsListAdapter;
    RecyclerView rvCollections;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getActivity());
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

        rvCollections = view.findViewById(R.id.rvCollections);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvCollections.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvCollections.getContext(), layoutManager.getOrientation());
        rvCollections.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onStart() {
        super.onStart();
        collectionsPresenter.attachScreen(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        collectionsPresenter.listCollections();
    }

    @Override
    public void onStop() {
        super.onStop();
        collectionsPresenter.detachScreen();
    }

    @Override
    public void showCollectionsList(List<Joke> jokes) {
        if(collectionsListAdapter == null) {
            collectionsListAdapter = new CollectionsListAdapter(getContext(), jokes);
            rvCollections.setAdapter(collectionsListAdapter);
            collectionsListAdapter.setOnClickListener(this);
        }
        else {
            collectionsListAdapter.updateItems(jokes);
        }
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "collection");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "list");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, bundle);
    }

    @Override
    public void onItemDeleteClick(int id) {
        collectionsPresenter.removeFromCollection(id);
    }
}