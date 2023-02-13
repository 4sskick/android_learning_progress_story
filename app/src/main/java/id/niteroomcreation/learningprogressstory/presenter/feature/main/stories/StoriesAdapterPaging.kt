package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.niteroomcreation.learningprogressstory.databinding.IStoriesBinding
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.detail.StoryDetailActivity
import id.niteroomcreation.learningprogressstory.util.dateFormatted

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoryDiffCallback() : DiffUtil.ItemCallback<Story>() {
    override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
        return oldItem == newItem
    }
}

class StoriesAdapterPaging() :
    PagingDataAdapter<Story, StoriesAdapterPaging.ViewHolder>(StoryDiffCallback()) {

    companion object {
        val TAG = StoriesAdapterPaging::class.java.simpleName
    }

    class ViewHolder(binding: IStoriesBinding) : RecyclerView.ViewHolder(binding.root) {
        var image: ImageView = binding.iImage
        var username: TextView = binding.iUsername
        var date: TextView = binding.iDate
        var desc: TextView = binding.iDesc
        var iLayoutParent: CardView = binding.iParent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            IStoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val story = getItem(position)

        Glide.with(holder.image.context).load(story?.photoUrl)
            .fitCenter()
            .into(holder.image)

        story?.apply {
            holder.apply {
                username.text = name
                desc.text = description
                date.text = createdAt.dateFormatted()
            }
        }

        holder.iLayoutParent.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {

                holder.itemView.context.startActivity(
                    Intent(holder.itemView.context, StoryDetailActivity::class.java).also {
                        it.putExtra(StoryDetailActivity.DATA, story)
                    },
                    ActivityOptions.makeSceneTransitionAnimation(
                        holder.itemView.context as Activity,
                        Pair(holder.image, "detail_image"),
                        Pair(holder.username, "detail_posted_by_val"),
                        Pair(holder.desc, "detail_desc_val"),
                        Pair(holder.date, "detail_date_val"),
                    ).toBundle()
                )

            }
        })
    }
}