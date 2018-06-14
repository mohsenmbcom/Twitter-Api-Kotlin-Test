package com.mohsenmb.twitterauthsearchkotlinsample.di

import com.mohsenmb.twitterauthsearchkotlinsample.view.activity.MainActivity
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.AuthorizationFragment
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.SearchTweetsFragment
import dagger.Component
import dagger.Subcomponent
import javax.inject.Singleton


/**
 * Created by mohsen on 6/11/18.
 */
@Singleton
@Component(modules = [AndroidModule::class, ApiModule::class])
interface MainComponent {
    fun plus(authorizationModule: AuthorizationModule): AuthorizationComponent
    fun plus(searchTweetsModule: SearchTweetsModule): SearchTweetsComponent
}

@Subcomponent(modules = [AuthorizationModule::class])
interface AuthorizationComponent {
    fun inject(authorizationFragment: AuthorizationFragment)
}

@Subcomponent(modules = [SearchTweetsModule::class])
interface SearchTweetsComponent {
    fun inject(searchTweetsFragment: SearchTweetsFragment)
}