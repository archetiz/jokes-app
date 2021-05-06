package kk.jokesapp.ui.joke;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.model.Joke;

@AndroidEntryPoint
public class RandomJokeFragment extends Fragment implements RandomJokeScreen {

    @Inject
    RandomJokePresenter randomJokePresenter;

    private TextView tvRandomJokeSetup;
    private TextView tvRandomJokePunchline;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_random_joke, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvRandomJokeSetup = view.findViewById(R.id.tvRandomJokeSetup);
        tvRandomJokePunchline = view.findViewById(R.id.tvRandomJokePunchline);

        Button saveJoke = view.findViewById(R.id.bSaveJoke);
        saveJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                randomJokePresenter.saveCurrentJoke();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        randomJokePresenter.attachScreen(this);
        randomJokePresenter.showRandomJoke();
    }

    @Override
    public void onStop() {
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
        View view = getView();
        if(view != null) {
            Snackbar.make(view, R.string.succesful_save, Snackbar.LENGTH_LONG)
                    .setBackgroundTint(getActivity().getResources().getColor(R.color.success))
                    .show();
        }
    }
}