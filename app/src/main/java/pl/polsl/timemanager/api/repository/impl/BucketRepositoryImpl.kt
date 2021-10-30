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
}