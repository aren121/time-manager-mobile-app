package pl.polsl.timemanager.ui.buckets.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_bucket_details.*
import pl.polsl.timemanager.R
import pl.polsl.timemanager.ui.buckets.BucketsViewModel
import pl.polsl.timemanager.ui.buckets.BucketsViewModelImpl
import pl.polsl.timemanager.ui.tasks.TaskListFragment

class BucketDetailsFragment : Fragment() {

    private lateinit var bucketsViewModel: BucketsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = requireActivity()
        bucketsViewModel =
            ViewModelProvider(activity).get(BucketsViewModelImpl::class.java)

        return inflater.inflate(R.layout.fragment_bucket_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bucketDetailsViewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(bucketDetailsTabLayout, bucketDetailsViewPager) { tab, position ->
            when(position) {
                0 -> tab.text = getString(R.string.bucketDetailsTasksTabTitle)
                1 -> tab.text = getString(R.string.bucketDetailsDetailsTabTitle)
            }
        }.attach()

        bucketsViewModel.currentBucket.observe(viewLifecycleOwner) { currentBucket ->
            (activity as AppCompatActivity).supportActionBar?.title =
                currentBucket?.bucketName ?: getString(R.string.bucketDetailsTitle)
        }
    }

    class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount(): Int {
            return 2
        }

        override fun createFragment(position: Int): Fragment {
            return TaskListFragment()
        }


    }

}