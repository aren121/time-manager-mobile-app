package pl.polsl.timemanager.ui.buckets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_bucket_action.*
import pl.polsl.timemanager.R

class BucketActionFragment : Fragment() {

    private lateinit var bucketsViewModel: BucketsViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity()
        bucketsViewModel =
            ViewModelProvider(activity).get(BucketsViewModelImpl::class.java)

        navController = activity.findNavController(R.id.nav_host_fragment)
        return inflater.inflate(R.layout.fragment_bucket_action, container, false)
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
            bucketsViewModel.saveBucket(
                bucketName.text.toString(),
                bucketDescription.text.toString(),
                bucketMaxTasks.text.toString()
            )
        }

        bucketsViewModel.currentBucket.observe(viewLifecycleOwner)  { bucket ->
            bucket?.let {
                navController.navigate(R.id.navigation_bucket_details)
            }
        }
    }

}