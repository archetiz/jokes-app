package kk.jokesapp.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.testing.TestInstallIn;
import kk.jokesapp.network.JokesApi;
import kk.jokesapp.network.NetworkModule;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@TestInstallIn(
        components = SingletonComponent.class,
        replaces = NetworkModule.class
)
public class MockNetworkModule {
    @Provides
    @Singleton
    public static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
    }

    @Provides
    @Singleton
    public static JokesApi provideJokesApi(Retrofit.Builder retrofitBuilder) {
        return new MockJokesApi();
    }
}
