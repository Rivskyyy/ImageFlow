package com.rivskyinc.imageflow.presentation.MainFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.rivskyinc.imageflow.domain.GetImageUseCase
import com.rivskyinc.imageflow.domain.entities.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

import javax.inject.Inject

class MainViewModel @Inject  constructor(private val getImageUseCase: GetImageUseCase):  ViewModel()  {

    private var imageList= MutableLiveData<Image>()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                getLatestPhoto()
            }
        }

    }

    private suspend fun getLatestPhoto(){
        try {
            val data = getImageUseCase.invoke()
            imageList.postValue(data)
        } catch (e: HttpException) {
            throw RuntimeException("HttpException : $e ")
        } catch (e: IOException) {
            throw RuntimeException("IOException : $e ")
        }
    }

}