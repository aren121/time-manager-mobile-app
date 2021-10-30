package pl.polsl.timemanager.ui.buckets

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.fragment_bucket_action.*
import pl.polsl.timemanager.R

class BucketActionFragment : Fragment() {

    private lateinit var bucketsViewModel: BucketsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bucketsViewModel =
            ViewModelProvider(requireActivity()).get(BucketsViewModelImpl::class.java)

        val root = inflater.inflate(R.layout.fragment_bucket_action, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bucketName.setText("")
        bucketDescription.setText("")
        bucketMaxTasks.setText("")

        bucketsViewModel.editedBucket.observe(viewLifecycleOwner) { editedBucket ->
            editedBucket?.let {
                (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.editBucketTitle)
                bucketName.setText(it.bucketName ?: "")
                bucketDescription.setText(it.description)
                bucketMaxTasks.setText(it.maxTasks.toString())
            }
        }
        saveBucketButton.setOnClickListener {
            //bucketsViewModel.createBucket()
        }
    }

}