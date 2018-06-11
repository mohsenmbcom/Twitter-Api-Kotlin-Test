package com.mohsenmb.twitterauthsearchkotlinsample.di

import com.mohsenmb.twitterauthsearchkotlinsample.view.activity.MainActivity
import com.mohsenmb.twitterauthsearchkotlinsample.view.fragment.AuthorizationFragment
import dagger.Component
import dagger.Subcomponent
import dagger.android.AndroidInjector
import javax.inject.Singleton


/**
 * Created by mohsen on 6/11/18.
 */
@Singleton
@Component(modules = [AndroidModule::class, ApiModule::class])
interface MainComponent {
    fun plus(authorizationModule: AuthorizationModule): AuthorizationComponent
}

@Subcomponent(modules = [AuthorizationModule::class])
interface AuthorizationComponent : AndroidInjector<MainActivity> {
    fun inject(authorizationFragment: AuthorizationFragment)
}