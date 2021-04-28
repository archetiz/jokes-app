package kk.jokesapp.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create());
    }

    @Provides
    @Singleton
    public static JokesApi provideJokesApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(NetworkConfig.ENDPOINT_ADDRESS).build().create(JokesApi.class);
    }
}
