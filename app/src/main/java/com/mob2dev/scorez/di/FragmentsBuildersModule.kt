package com.mob2dev.scorez.di

import com.mob2dev.scorez.ui.activities.DevSetting
import com.mob2dev.scorez.ui.activities.Login
import com.mob2dev.scorez.ui.activities.Register
import com.mob2dev.scorez.ui.activities.SplashScreen
import com.mob2dev.scorez.ui.fragments.Overview
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashScreenFragment(): SplashScreen


}