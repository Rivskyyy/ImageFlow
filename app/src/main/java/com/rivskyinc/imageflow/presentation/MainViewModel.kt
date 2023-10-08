package com.rivskyinc.imageflow.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rivskyinc.imageflow.domain.GetImageUseCase
import com.rivskyinc.imageflow.domain.entities.Image

import javax.inject.Inject

class MainViewModel @Inject  constructor(private val getImageUseCase: GetImageUseCase):  ViewModel()  {

    private var imageList= MutableLiveData<Image>()


}