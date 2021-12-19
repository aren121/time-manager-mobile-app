package pl.polsl.timemanager.ui.buckets

import androidx.lifecycle.MutableLiveData
import pl.polsl.timemanager.api.RepositoryHolder
import pl.polsl.timemanager.base.subscribeAndPush
import pl.polsl.timemanager.model.Bucket

class BucketsViewModelImpl : BucketsViewModel() {

    override val buckets = MutableLiveData<List<Bucket>>()
    override val editedBucket = MutableLiveData<Bucket?>()
    override val currentBucket = MutableLiveData<Bucket?>()
    private val bucketRepository = RepositoryHolder.bucketRepository

    init {
        fetchOwnBuckets()
    }

    private fun fetchOwnBuckets() {
        compositeDisposable.subscribeAndPush(bucketRepository.getOwnBuckets(), ::onError) { result ->
            result.onSuccess {
                buckets.value = it
            }.onFailure(::onError)
        }
    }

    override fun init() {

    }

    override fun onEditBucket(bucket: Bucket) {
        editedBucket.value = bucket
        currentBucket.value = null
    }

    override fun onCreateBucket() {
        editedBucket.value = null
        currentBucket.value = null
    }

    override fun onShowBucket(bucket: Bucket) {
        editedBucket.value = null
        currentBucket.value = bucket
    }

    override fun saveBucket(bucketName: String, description: String, maxTasks: String) {
        if(editedBucket.value == null) {

            val bucket = Bucket(
                bucketName = bucketName, description = description, maxTasks = maxTasks.toInt()
            )

            compositeDisposable.subscribeAndPush(bucketRepository.createBucket(bucket),
                ::onError) { result ->
                result.onSuccess {
                    fetchOwnBuckets()
                    currentBucket.value = it
                }.onFailure(::onError)
            }
        }

        editedBucket.value?.let {
            val bucket = Bucket(
                idBucket = it.idBucket, bucketName = bucketName, description = description, maxTasks = maxTasks.toInt()
            )

            compositeDisposable.subscribeAndPush(bucketRepository.editBucket(bucket),
                ::onError) { result ->
                result.onSuccess { editedBucketObj ->
                    currentBucket.value = editedBucketObj
                }.onFailure(::onError)
            }

        }
    }

}