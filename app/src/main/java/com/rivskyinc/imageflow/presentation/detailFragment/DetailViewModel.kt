package com.rivskyinc.imageflow.presentation.detailFragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.load.HttpException
import com.rivskyinc.imageflow.domain.UseCases.GetImageDetailUseCase
import com.rivskyinc.imageflow.domain.entities.PhotoX.Result
import java.io.IOException
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val getImageDetailUseCase: GetImageDetailUseCase) : ViewModel() {

    private var _imageDetail= MutableLiveData<Result>()
    val imageDetail : LiveData<Result> = _imageDetail

     suspend fun getImageDetail(id : String ){
        try {
            val data = getImageDetailUseCase.invoke(id)
            Log.d("DetailViewModel", data.toString())
            _imageDetail.postValue(data)
        } catch (e: HttpException) {
            throw RuntimeException("HttpException : $e ")
        } catch (e: IOException) {
            throw RuntimeException("IOException : $e ")
        }
    }
}