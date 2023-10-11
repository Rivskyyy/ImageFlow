package com.rivskyinc.imageflow.presentation.detailFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rivskyinc.imageflow.domain.UseCases.GetImageDetailUseCase
import javax.inject.Inject

class DetailViewModelFactory @Inject constructor(private val getImageDetailUseCase: GetImageDetailUseCase)
    : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(getImageDetailUseCase) as T
        }
        throw RuntimeException("Unknown ViewModel class $modelClass")
    }
}