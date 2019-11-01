package com.mob2dev.scorez.di

import com.mob2dev.scorez.RouterActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeActivityBuilder(): RouterActivity
}