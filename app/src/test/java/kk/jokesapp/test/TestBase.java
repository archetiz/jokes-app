package kk.jokesapp.test;

import android.os.Looper;

import org.junit.Rule;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;
import kk.jokesapp.database.AppDatabase;

import static org.robolectric.Shadows.shadowOf;

@HiltAndroidTest
public abstract class TestBase {
    @Rule
    public HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Inject
    AppDatabase testDatabase;

    protected void closeDatabase() {
        testDatabase.close();
    }

    protected void waitForTasks() {
        shadowOf(Looper.getMainLooper()).idle();
    }
}
