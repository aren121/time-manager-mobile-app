package pl.polsl.timemanager.ui.buckets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_buckets.*
import pl.polsl.timemanager.R
import pl.polsl.timemanager.model.Bucket

class BucketsFragment : Fragment(), BucketViewHolderActionListener {

    private lateinit var bucketsViewModel: BucketsViewModel
    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = requireActivity()
        bucketsViewModel =
                ViewModelProvider(activity).get(BucketsViewModelImpl::class.java)

        navController = activity.findNavController(R.id.nav_host_fragment)
        return inflater.inflate(R.layout.fragment_buckets, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bucketsRecyclerView.layoutManager = LinearLayoutManager(context)

        bucketsViewModel.buckets.observe(viewLifecycleOwner) {
            bucketsRecyclerView.adapter = BucketsRecyclerViewAdapter(it, true, this)
        }

        bucketsViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        }

        createBucketButton.setOnClickListener {
            navController.navigate(R.id.navigation_bucket_action)
        }

    }

    override fun onBucketEdit(bucket: Bucket) {
        bucketsViewModel.onEditBucket(bucket)
        navController.navigate(R.id.navigation_bucket_action)
    }
}