package com.example.cleanarchitectur.di.data

import com.example.cleanarchitectur.BuildConfig
import com.example.cleanarchitectur.data.datasource.network.ApiService
import com.example.cleanarchitectur.data.datasource.network.CartonApiService
import com.example.cleanarchitectur.data.repository.ApiRepositoryImpl
import com.example.cleanarchitectur.domain.repository.ApiRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val networkModule = module {


    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(loggingInterceptor = get()) }
    single { provideJson() }
    single { provideRetrofit(okHttpClient = get(), jsonConverter = get()) }
    single { provideCartonApiService(retrofit = get()) }
    single { provideApiService(retrofit = get()) }
    single<ApiRepository> { ApiRepositoryImpl(apiService = get(), ioDispatcher = get(named("IO")), cartonApiService = get()) }

}

fun provideRetrofit(okHttpClient: OkHttpClient, jsonConverter: Converter.Factory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.THE_RICK_AND_MORTY_API)
        .client(okHttpClient)
        .addConverterFactory(jsonConverter)
        .build()
}

fun provideCartonApiService(retrofit: Retrofit): CartonApiService {
    return retrofit.create(CartonApiService::class.java)
}
fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}


fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .addInterceptor(loggingInterceptor)
        .build()
}

fun provideJson(): Converter.Factory {
    val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }
    return json.asConverterFactory("application/json".toMediaType())
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
