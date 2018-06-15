package com.mohsenmb.twitterauthsearchkotlinsample.di

import com.mohsenmb.twitterauthsearchkotlinsample.service.TwitterTestApiService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by mohsen on 6/15/18.
 */
@Singleton
@Component(modules = [AndroidModule::class, ApiModule::class])
interface MainTestComponent : MainComponent {
    fun inject(twitterTestApiService: TwitterTestApiService)
}