package com.rivskyinc.imageflow.presentation.mainFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.HttpException
import com.rivskyinc.imageflow.domain.UseCases.GetImageUseCase
import com.rivskyinc.imageflow.domain.entities.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

import javax.inject.Inject

class MainViewModel @Inject  constructor(private val getImageUseCase: GetImageUseCase):  ViewModel()  {

    private var _imageList= MutableLiveData<Image>()
    val imageList : LiveData<Image> = _imageList

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
            Log.d("MainViewModel", data.toString())
            _imageList.postValue(data)
        } catch (e: HttpException) {
            throw RuntimeException("HttpException : $e ")
        } catch (e: IOException) {
            throw RuntimeException("IOException : $e ")
        }
    }

}