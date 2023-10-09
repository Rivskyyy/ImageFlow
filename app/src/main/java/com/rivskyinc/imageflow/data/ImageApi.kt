package com.rivskyinc.imageflow.data

import com.rivskyinc.imageflow.Utils.API_KEY
import com.rivskyinc.imageflow.domain.entities.Image
import com.rivskyinc.imageflow.domain.entities.PhotoX.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApi {

    @GET("rest/?method=flickr.interestingness.getList&api_key=$API_KEY&format=json&nojsoncallback=1") // <- put your API_KEY here
    suspend fun imageResponse() : Response<Image?>

    @GET("rest/?method=flickr.photos.getInfo&api_key=$API_KEY&format=json&nojsoncallback=1")
    suspend fun imageGetInfo(
        @Query("photo_id")
        id : String
    ) : Response<Result?>

}