package tk.zedlabs.wallportal.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import tk.zedlabs.wallportal.R
import tk.zedlabs.wallportal.repository.BookmarkImage

class BookmarkAdapter(
    private var bookmarkedImages: List<BookmarkImage>,
    private val onImageListener: OnImageListener
) : RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder>() {

    inner class BookmarkViewHolder(imageView: ImageView, onImageListener: OnImageListener) :
        RecyclerView.ViewHolder(imageView), View.OnClickListener {

        init {
            imageView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onImageListener.onImageClick(adapterPosition)
        }
    }

    interface OnImageListener {
        fun onImageClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false) as ImageView

        return BookmarkViewHolder(
            imageView,
            onImageListener
        )
    }

    override fun getItemCount() = bookmarkedImages.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {

        val circularProgressDrawable = CircularProgressDrawable(holder.itemView.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(holder.itemView.context)
            .load(bookmarkedImages[position].imageUrlRegular!!)
            .placeholder(circularProgressDrawable)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.itemView.imageViewItem)
    }

}