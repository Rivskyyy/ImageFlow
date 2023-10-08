package com.rivskyinc.imageflow.presentation.MainFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivskyinc.imageflow.domain.GetImageUseCase
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val getImageUseCase: GetImageUseCase) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(getImageUseCase) as T
        }
        throw RuntimeException("Unknown ViewModel class $modelClass")

    }
}
