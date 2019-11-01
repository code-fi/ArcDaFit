package com.mob2dev.scorez.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module
abstract class ViewModelBuilder {

    //    @Binds
//    @IntoMap
//    @ViewModelKey(AuthenticationViewModel::class)
//    abstract fun bindAuthenticationViewModel(authenticationViewModel: AuthenticationViewModel): ViewModel
//

    @Binds
    abstract fun bindViewModelFactory(factory: MobViewModelFactory): ViewModelProvider.Factory
}