package pl.polsl.timemanager.ui.buckets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.bucket_list_item.view.*
import pl.polsl.timemanager.R
import pl.polsl.timemanager.model.Bucket

class BucketsRecyclerViewAdapter(private var buckets: List<Bucket>,
                                 private val isOwn: Boolean,
                                 private val actionListener: BucketViewHolderActionListener)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class BucketViewHolder(itemView: View, private val context: Context,
                           private val actionListener: BucketViewHolderActionListener) : RecyclerView.ViewHolder(itemView) {
        private lateinit var bucket: Bucket

        init {
            itemView.editButton.setOnClickListener {
                actionListener.onBucketEdit(bucket)
            }

            itemView.showButton.setOnClickListener {
                actionListener.onBucketShow(bucket)
            }
        }

        fun bind(bucket: Bucket, isOwn: Boolean) {
            this.bucket = bucket
            itemView.bucketName.text = bucket.bucketName
            itemView.bucketDescription.text = bucket.description

            itemView.shareButton.isVisible = isOwn
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BucketViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.bucket_list_item, parent, false), parent.context, actionListener)
    }

    override fun getItemCount(): Int {
        return buckets.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BucketViewHolder).bind(buckets[position], isOwn)
    }
}