package pl.polsl.timemanager.api.repository

import io.reactivex.rxjava3.core.Single
import pl.polsl.timemanager.model.Bucket

interface BucketRepository {

    fun getOwnBuckets(): Single<Result<List<Bucket>>>

    fun createBucket(bucket: Bucket): Single<Result<Bucket>>

    fun editBucket(bucket: Bucket): Single<Result<Bucket>>

}