package com.mohsenmb.twitterauthsearchkotlinsample.di

import com.mohsenmb.twitterauthsearchkotlinsample.service.TwitterTestApiService
import com.mohsenmb.twitterauthsearchkotlinsample.service.TwitterWebService
import retrofit2.Retrofit

/**
 * Created by mohsen on 6/15/18.
 */
class ApiTestModule: ApiModule() {
    override fun provideApiService(retrofit: Retrofit): TwitterWebService {
        return TwitterTestApiService()
    }
}