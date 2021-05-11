package kk.jokesapp.test;

import android.os.Looper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.HiltTestApplication;
import kk.jokesapp.database.AppDatabase;
import kk.jokesapp.ui.add.NewJokePresenter;
import kk.jokesapp.ui.add.NewJokeScreen;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@HiltAndroidTest
@RunWith(RobolectricTestRunner.class)
@Config(application = HiltTestApplication.class)
public class NewJokeTest extends TestBase {
    @Inject
    NewJokePresenter newJokePresenter;

    NewJokeScreen newJokeScreen;

    @Inject
    AppDatabase testDatabase;

    @Before
    public void initTest() {
        hiltRule.inject();
        newJokeScreen = mock(NewJokeScreen.class);
        newJokePresenter.attachScreen(newJokeScreen);
    }

    @Test
    public void testAddNewJoke() {
        newJokePresenter.saveNewJoke("test", "TestSetup", "TestPunchline");

        shadowOf(Looper.getMainLooper()).idle();

        ArgumentCaptor<Boolean> saveResultCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<List> saveErrorListCaptor = ArgumentCaptor.forClass(List.class);
        verify(newJokeScreen).showSaveResult(saveResultCaptor.capture(), saveErrorListCaptor.capture());

        assertTrue(saveResultCaptor.getValue());
        assertNull(saveErrorListCaptor.getValue());
    }

    @Test
    public void testAddEmptyJoke() {
        newJokePresenter.saveNewJoke("test", "", "");

        shadowOf(Looper.getMainLooper()).idle();

        ArgumentCaptor<Boolean> saveResultCaptor = ArgumentCaptor.forClass(Boolean.class);
        ArgumentCaptor<List> saveErrorListCaptor = ArgumentCaptor.forClass(List.class);
        verify(newJokeScreen).showSaveResult(saveResultCaptor.capture(), saveErrorListCaptor.capture());

        assertFalse(saveResultCaptor.getValue());
        assertTrue(saveErrorListCaptor.getValue().size() > 0);
    }

    @After
    public void finishTest() {
        newJokePresenter.detachScreen();
        testDatabase.close();
    }
}
