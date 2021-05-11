package kk.jokesapp.test;

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
import kk.jokesapp.interactor.CollectionsInteractor;
import kk.jokesapp.model.Joke;
import kk.jokesapp.ui.collections.CollectionsPresenter;
import kk.jokesapp.ui.collections.CollectionsScreen;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@HiltAndroidTest
@RunWith(RobolectricTestRunner.class)
@Config(application = HiltTestApplication.class)
public class CollectionsTest extends TestBase {

    @Inject
    CollectionsPresenter collectionsPresenter;

    CollectionsScreen collectionsScreen;

    @Inject
    CollectionsInteractor collectionsInteractor;

    @Before
    public void initTest() {
        hiltRule.inject();
        collectionsScreen = mock(CollectionsScreen.class);
        collectionsPresenter.attachScreen(collectionsScreen);

        collectionsInteractor.addJoke(new Joke(21, "test", "TestSetup", "TestPunchline"));
    }

    @Test
    public void testListJokes() {
        collectionsPresenter.listCollections();

        waitForTasks();

        ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        verify(collectionsScreen).showCollectionsList(listCaptor.capture());

        assertTrue(listCaptor.getValue().size() > 0);
    }

    @Test
    public void testDeleteJoke() {
        collectionsPresenter.listCollections();

        waitForTasks();

        ArgumentCaptor<List> listCaptor = ArgumentCaptor.forClass(List.class);
        verify(collectionsScreen).showCollectionsList(listCaptor.capture());

        List<Joke> originalList = listCaptor.getValue();
        collectionsPresenter.removeFromCollection(originalList.get(0).getId());

        waitForTasks();

        ArgumentCaptor<List> newListCaptor = ArgumentCaptor.forClass(List.class);
        verify(collectionsScreen, atLeastOnce()).showCollectionsList(newListCaptor.capture());

        assertEquals(originalList.size() - 1, newListCaptor.getValue().size());
    }

    @After
    public void finishTest() {
        collectionsPresenter.detachScreen();
        closeDatabase();
    }
}