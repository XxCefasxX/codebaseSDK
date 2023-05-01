package com.cfsproj.code_base_sdk.di
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.cfsproj.code_base_sdk.rest.ServiceApi
import com.cfsproj.code_base_sdk.utils.BASE_URL
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesDispatcher(): CoroutineDispatcher =
        Dispatchers.IO


    @Provides
    fun providesNetworkApi(): ServiceApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                ).build()
            )
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(ServiceApi::class.java)
}