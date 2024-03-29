package pl.polsl.timemanager.ui.buckets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.polsl.timemanager.base.BaseViewModel
import pl.polsl.timemanager.model.Bucket

abstract class BucketsViewModel : BaseViewModel() {

    abstract val buckets: LiveData<List<Bucket>>

    abstract val editedBucket: LiveData<Bucket?>

    abstract val currentBucket: LiveData<Bucket?>

    abstract fun init()

    abstract fun onEditBucket(bucket: Bucket)

    abstract fun onCreateBucket()

    abstract fun onShowBucket(bucket: Bucket)

    abstract fun saveBucket(bucketName: String, description: String, maxTasks: String)

}