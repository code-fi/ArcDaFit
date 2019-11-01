package com.mob2dev.scorez.viewModel


import androidx.lifecycle.ViewModel
import com.mob2dev.scorez.repository.MobRepository
import javax.inject.Inject

class AppViewModel @Inject
constructor(private val mobRepository: MobRepository) : ViewModel() {


}
