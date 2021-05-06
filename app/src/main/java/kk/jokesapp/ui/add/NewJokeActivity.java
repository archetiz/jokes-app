package kk.jokesapp.ui.add;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;

@AndroidEntryPoint
public class NewJokeActivity extends AppCompatActivity implements NewJokeScreen {

    @Inject
    NewJokePresenter newJokePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_joke);

        Toolbar toolbar = findViewById(R.id.addToolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        newJokePresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        newJokePresenter.detachScreen();
    }

    @Override
    public void showSaveResult(Boolean success, List<String> errors) {
        //TODO
    }
}