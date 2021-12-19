package pl.polsl.timemanager.api.repository.impl

import io.reactivex.rxjava3.core.Single
import pl.polsl.timemanager.api.RetrofitProvider
import pl.polsl.timemanager.api.repository.BucketRepository
import pl.polsl.timemanager.api.service.BucketService
import pl.polsl.timemanager.model.Bucket

class BucketRepositoryImpl : BucketRepository {

    private val bucketApi = RetrofitProvider.createService(BucketService::class.java)

    override fun getOwnBuckets(): Single<Result<List<Bucket>>> {
        return Single.create {
            bucketApi.getOwnBuckets().enqueue(RxCallbackImpl(it))
        }
    }

    override fun createBucket(bucket: Bucket): Single<Result<Bucket>> {
        return Single.create {
            bucketApi.createBucket(bucket).enqueue(RxCallbackImpl(it))
        }
    }

    override fun editBucket(bucket: Bucket): Single<Result<Bucket>> {
        assert(bucket.idBucket != null) { "Passed no id bucket to api call" }
        return Single.create {
            bucketApi.editBucket(bucket, bucket.idBucket!!).enqueue(RxCallbackImpl(it))
        }
    }
}