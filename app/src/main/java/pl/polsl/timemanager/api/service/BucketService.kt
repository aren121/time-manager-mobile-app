package pl.polsl.timemanager.api.service

import pl.polsl.timemanager.model.Bucket
import retrofit2.Call
import retrofit2.http.*

interface BucketService {

    @GET("bucket/own")
    fun getOwnBuckets() : Call<List<Bucket>>

    @POST("bucket")
    fun createBucket(@Body bucket: Bucket) : Call<Bucket>

    @PUT("bucket/{idBucket}")
    fun editBucket(@Body bucket: Bucket, @Path("idBucket") idBucket: Int) : Call<Bucket>

}