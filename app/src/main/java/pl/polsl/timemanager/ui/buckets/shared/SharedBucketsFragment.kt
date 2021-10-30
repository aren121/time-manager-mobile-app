package pl.polsl.timemanager.ui.buckets.shared

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pl.polsl.timemanager.R

class SharedBucketsFragment : Fragment() {

    private lateinit var viewModel: SharedBucketsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProvider(this).get(SharedBucketsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_shared_buckets, container, false)
    }



}