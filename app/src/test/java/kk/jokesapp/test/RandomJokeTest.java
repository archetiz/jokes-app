package kk.jokesapp.test;

import android.os.Looper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import javax.inject.Inject;

import dagger.hilt.android.testing.HiltAndroidTest;
import dagger.hilt.android.testing.HiltTestApplication;
import kk.jokesapp.database.AppDatabase;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.joke.RandomJokePresenter;
import kk.jokesapp.ui.joke.RandomJokeScreen;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Shadows.shadowOf;

@HiltAndroidTest
@RunWith(RobolectricTestRunner.class)
@Config(application = HiltTestApplication.class)
public class RandomJokeTest extends TestBase {

    @Inject
    RandomJokePresenter randomJokePresenter;

    RandomJokeScreen randomJokeScreen;

    @Inject
    AppDatabase testDatabase;

    @Before
    public void initTest() {
        hiltRule.inject();
        randomJokeScreen = mock(RandomJokeScreen.class);
        randomJokePresenter.attachScreen(randomJokeScreen);
    }

    @Test
    public void testShowRandomJoke() {
        randomJokePresenter.showRandomJoke();

        shadowOf(Looper.getMainLooper()).idle();

        ArgumentCaptor<Joke> jokeCaptor = ArgumentCaptor.forClass(Joke.class);
        verify(randomJokeScreen).showRandomJoke(jokeCaptor.capture());

        Joke capturedJoke = jokeCaptor.getValue();

        assertEquals(42, capturedJoke.getId());
        assertEquals("test", capturedJoke.getType());
        assertEquals("TestSetup", capturedJoke.getSetup());
        assertEquals("TestPunchline", capturedJoke.getPunchline());
    }

    @Test
    public void testSaveRandomJoke() {
        randomJokePresenter.showRandomJoke();
        randomJokePresenter.saveCurrentJoke();

        shadowOf(Looper.getMainLooper()).idle();

        ArgumentCaptor<Boolean> saveResultCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(randomJokeScreen).showSaveResult(saveResultCaptor.capture());

        assertTrue(saveResultCaptor.getValue());
    }

    @After
    public void finishTest() {
        randomJokePresenter.detachScreen();
        testDatabase.close();
    }
}
