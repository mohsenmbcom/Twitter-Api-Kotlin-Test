package com.mohsenmb.twitterauthsearchkotlinsample.di

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.mohsenmb.twitterauthsearchkotlinsample.TwitterApplication
import com.mohsenmb.twitterauthsearchkotlinsample.interaction.AuthorizationInteractor
import com.mohsenmb.twitterauthsearchkotlinsample.interaction.AuthorizationInteractorImpl
import com.mohsenmb.twitterauthsearchkotlinsample.interaction.SearchInteractor
import com.mohsenmb.twitterauthsearchkotlinsample.interaction.SearchInteractorImpl
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.AuthorizationPresenter
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.AuthorizationPresenterImpl
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.SearchPresenter
import com.mohsenmb.twitterauthsearchkotlinsample.presentation.SearchPresenterImpl
import com.mohsenmb.twitterauthsearchkotlinsample.service.TwitterWebService
import com.mohsenmb.twitterauthsearchkotlinsample.service.model.Token
import com.mohsenmb.twitterauthsearchkotlinsample.util.SessionManager
import com.mohsenmb.twitterauthsearchkotlinsample.view.AuthorizationView
import com.mohsenmb.twitterauthsearchkotlinsample.view.SearchTweetsView
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by mohsen on 6/11/18.
 */
const val API_BASE_URL: String = "https://api.twitter.com"

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): TwitterWebService {
        return retrofit.create(TwitterWebService::class.java)
    }


    @Provides
    @Singleton
    fun provideRetrofit(@Named("apiBaseUrl") baseUrl: String, client: OkHttpClient, converterFactory: Converter.Factory,
                        callAdapterFactory: CallAdapter.Factory): Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .build()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().excludeFieldsWithoutExposeAnnotation().create()

    @Provides
    @Singleton
    @Named("apiBaseUrl")
    fun provideApiBaseUrl(): String = API_BASE_URL

    @Provides
    @Singleton
    fun provideRxJavaCallAdapterFactory(): CallAdapter.Factory = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideAccessToken(sessionManager: SessionManager): Token = sessionManager.getSessionToken()
}

@Module
class AndroidModule constructor(private val twitterApplication: TwitterApplication) {
    @Provides
    @Singleton
    fun provideResources(): Resources = twitterApplication.resources

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
            twitterApplication.getSharedPreferences(twitterApplication.packageName, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideApplicationContext(): Context =
            twitterApplication.applicationContext
}

@Module
class AuthorizationModule(private val authorizationView: AuthorizationView) {

    @Provides
    fun provideAuthorizationView(): AuthorizationView = authorizationView

    @Provides
    fun provideAuthorizationPresenter(presenter: AuthorizationPresenterImpl):
            AuthorizationPresenter {
        presenter.setView(authorizationView)
        return presenter
    }

    @Provides
    fun provideAuthorizationInteractor(interactor: AuthorizationInteractorImpl):
            AuthorizationInteractor = interactor
}

@Module
class SearchTweetsModule(private val searchTweetsView: SearchTweetsView) {
    @Provides
    fun provideSearchTweetsView(): SearchTweetsView = searchTweetsView

    @Provides
    fun provideSearchTweetsPresenter(presenter: SearchPresenterImpl): SearchPresenter {
        presenter.searchTweetsView = searchTweetsView
        return presenter
    }

    @Provides
    fun provideSearchTweetsInteractor(interactor: SearchInteractorImpl): SearchInteractor = interactor
}