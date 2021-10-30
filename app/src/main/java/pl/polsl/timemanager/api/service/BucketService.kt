package pl.polsl.timemanager.api.service

import pl.polsl.timemanager.model.Bucket
import retrofit2.Call
import retrofit2.http.GET

interface BucketService {

    @GET("bucket/own")
    fun getOwnBuckets() : Call<List<Bucket>>

}