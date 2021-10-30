package pl.polsl.timemanager.ui.buckets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.polsl.timemanager.api.RepositoryHolder
import pl.polsl.timemanager.base.subscribeAndPush
import pl.polsl.timemanager.model.Bucket
import java.util.*

class BucketsViewModelImpl : BucketsViewModel() {

    override val buckets = MutableLiveData<List<Bucket>>()
    override val editedBucket = MutableLiveData<Bucket?>()
    private val bucketRepository = RepositoryHolder.bucketRepository

    init {
        compositeDisposable.subscribeAndPush(bucketRepository.getOwnBuckets(), ::onError) { result ->
            result.onSuccess {
                buckets.value = it
            }.onFailure(::onError)
        }
    }

    override fun createBucket(bucket: Bucket) {

    }

    override fun init() {

    }

    override fun onEditBucket(bucket: Bucket) {
        editedBucket.value = bucket
    }

    override fun onCreateBucket() {
        editedBucket.value = null
    }

}