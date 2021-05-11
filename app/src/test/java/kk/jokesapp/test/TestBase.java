package kk.jokesapp.test;

import org.junit.Rule;
import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
public abstract class TestBase {
    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);
}
