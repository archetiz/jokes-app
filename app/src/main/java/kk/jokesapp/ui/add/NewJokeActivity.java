package kk.jokesapp.ui.add;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;

@AndroidEntryPoint
public class NewJokeActivity extends AppCompatActivity implements NewJokeScreen {

    @Inject
    NewJokePresenter newJokePresenter;

    private EditText etNewJokeCategory;
    private EditText etNewJokeSetup;
    private EditText etNewJokePunchline;
    private Button bCancel;
    private Button bSave;
    private Toolbar toolbar;

    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_joke);

        rootView = findViewById(android.R.id.content);

        etNewJokeCategory = findViewById(R.id.etNewJokeCategory);
        etNewJokeSetup = findViewById(R.id.etNewJokeSetup);
        etNewJokePunchline = findViewById(R.id.etNewJokePunchline);

        toolbar = findViewById(R.id.addToolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick();
            }
        });

        bCancel = findViewById(R.id.bAddCancel);
        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackClick();
            }
        });

        bSave = findViewById(R.id.bAddSave);
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newJokePresenter.saveNewJoke(etNewJokeCategory.getText().toString(),
                                                etNewJokeSetup.getText().toString(),
                                                etNewJokePunchline.getText().toString());
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

    public void onBackClick() {
        if(!etNewJokeCategory.getText().toString().isEmpty()
        || !etNewJokeSetup.getText().toString().isEmpty()
        || !etNewJokePunchline.getText().toString().isEmpty()) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.confirm)
                    .setMessage(R.string.confirm_dialog_text)
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            backClickFunction();
                        }
                    })
                    .setNegativeButton(R.string.cancel, null)
                    .show();
        }
        else {
            backClickFunction();
        }
    }

    private void backClickFunction() {
        finish();
    }

    @Override
    public void showSaveResult(Boolean success, List<Integer> errors) {
            if(success) {
                Snackbar.make(rootView, R.string.succesful_save, Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.success))
                        .show();
                bCancel.setVisibility(View.INVISIBLE);
                bSave.setVisibility(View.INVISIBLE);
                toolbar.setNavigationOnClickListener(null);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
            }
            else {
                String errorText = "";
                if(errors != null && errors.size() > 0) {
                    for(Integer error : errors) {
                        errorText += getResources().getString(error) + System.lineSeparator();
                    }
                }
                else {
                    errorText = getResources().getString(R.string.unsuccesful_save);
                }
                Snackbar.make(rootView, errorText, Snackbar.LENGTH_LONG)
                        .setBackgroundTint(getResources().getColor(R.color.error))
                        .show();
            }
    }
}