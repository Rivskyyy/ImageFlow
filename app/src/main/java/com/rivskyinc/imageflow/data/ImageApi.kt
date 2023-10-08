package com.rivskyinc.imageflow.data

import com.rivskyinc.imageflow.Utils.API_KEY
import com.rivskyinc.imageflow.domain.entities.Image
import retrofit2.http.GET
import retrofit2.Response

interface ImageApi {

    @GET("rest/?method=flickr.interestingness.getList&api_key=$API_KEY") // <- put your API_KEY here
    suspend fun imageResponse() : Response<Image?>

}