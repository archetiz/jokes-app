package kk.jokesapp.mock;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import kk.jokesapp.database.AppDatabase;
import kk.jokesapp.database.DatabaseModule;

@Module
@TestInstallIn(
        components = SingletonComponent.class,
        replaces = DatabaseModule.class
)
public class MockDatabaseModule {

    @Provides
    @Singleton
    public static AppDatabase provideAppDatabase(@ApplicationContext Context context) {
        return Room.inMemoryDatabaseBuilder(context, AppDatabase.class).allowMainThreadQueries().build();
    }
}
