package kk.jokesapp.ui.tabs;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

import dagger.hilt.android.AndroidEntryPoint;
import kk.jokesapp.R;
import kk.jokesapp.ui.collections.CollectionsFragment;
import kk.jokesapp.ui.joke.RandomJokeFragment;

@AndroidEntryPoint
public class TabLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        TextView tvTitle = findViewById(R.id.tvTitle);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment contentFragment;
                switch (tab.getPosition()) {
                    default:
                    case 0:
                        contentFragment = new RandomJokeFragment();
                        tvTitle.setText(getResources().getText(R.string.random_joke));
                        break;
                    case 1:
                        contentFragment = new CollectionsFragment();
                        tvTitle.setText(getResources().getText(R.string.collections));
                        break;
                }
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.tabContentFrame, contentFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.tabContentFrame, new RandomJokeFragment())
                .commit();
    }
}
