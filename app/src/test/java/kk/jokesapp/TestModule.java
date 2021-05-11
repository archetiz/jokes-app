package kk.jokesapp;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import kk.jokesapp.annotation.DatabaseExecutor;
import kk.jokesapp.annotation.NetworkExecutor;
import kk.jokesapp.ui.UIModule;
import kk.jokesapp.util.UiExecutor;

@Module
@TestInstallIn(
        components = SingletonComponent.class,
        replaces = UIModule.class
)
public class TestModule {
    @Provides
    @Singleton
    @NetworkExecutor
    public static Executor provideNetworkExecutor() {
        return new UiExecutor();
    }

    @Provides
    @Singleton
    @DatabaseExecutor
    public static Executor provideDatabaseExecutor() {
        return new UiExecutor();
    }
}
