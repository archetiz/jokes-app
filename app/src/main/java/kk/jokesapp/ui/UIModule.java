package kk.jokesapp.ui;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kk.jokesapp.annotation.DatabaseExecutor;
import kk.jokesapp.annotation.NetworkExecutor;

@Module
@InstallIn(SingletonComponent.class)
public class UIModule {
    @Provides
    @Singleton
    @NetworkExecutor
    public static Executor provideNetworkExecutor() {
        return Executors.newFixedThreadPool(1);
    }

    @Provides
    @Singleton
    @DatabaseExecutor
    public static Executor provideDatabaseExecutor() {
        return Executors.newFixedThreadPool(1);
    }
}
