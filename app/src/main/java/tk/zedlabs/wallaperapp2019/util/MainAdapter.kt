package tk.zedlabs.wallaperapp2019.util

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import tk.zedlabs.wallaperapp2019.R
import tk.zedlabs.wallaperapp2019.util.MainAdapter.MyViewHolder
import tk.zedlabs.wallaperapp2019.models.WallHavenResponse

class MainAdapter(onImageListener: OnImageListener) :
    PagedListAdapter<WallHavenResponse, MyViewHolder>(diffCallback) {

    private lateinit var ctx : Context
    private var mOnImageListener: OnImageListener

    init {
        this.mOnImageListener = onImageListener
    }

    class MyViewHolder(imageView: ImageView,onImageListener: OnImageListener) :
        RecyclerView.ViewHolder(imageView),View.OnClickListener {

        var onImageListener : OnImageListener
        init {
            this.onImageListener = onImageListener
            imageView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            onImageListener.onImageClick(adapterPosition)
        }
    }

    interface OnImageListener{
        fun onImageClick(position: Int)
    }

    companion object {

        private val diffCallback = object : DiffUtil.ItemCallback<WallHavenResponse>() {
            override fun areItemsTheSame(oldItem: WallHavenResponse, newItem: WallHavenResponse): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: WallHavenResponse, newItem: WallHavenResponse): Boolean =
                oldItem.equals(newItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false) as ImageView
        ctx = parent.context
        return MyViewHolder(imageView,mOnImageListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)
        Glide.with(ctx)
            .load(post?.thumbs?.large)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.itemView.imageViewItem)
    }

}
