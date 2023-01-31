package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.niteroomcreation.learningprogressstory.databinding.IStoriesBinding
import id.niteroomcreation.learningprogressstory.domain.model.stories.Story
import id.niteroomcreation.learningprogressstory.presenter.listener.ItemViewClickListener
import id.niteroomcreation.learningprogressstory.util.dateFormatted

/**
 * Created by Septian Adi Wijaya on 26/01/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesAdapter(
    private var data: List<Story>,
    private var listener: ItemViewClickListener<Story>
) :
    RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    companion object {
        val TAG = StoriesAdapter::class.java.simpleName
    }

    fun update(data: List<Story>) {
        this.data = data
        notifyDataSetChanged()
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
        val story = data[position]

        Glide.with(holder.image.context).load(story.photoUrl)
            .fitCenter()
            .into(holder.image)

        story.apply {
            holder.apply {
                username.text = name
                desc.text = description
                date.text = createdAt.dateFormatted()
            }
        }

        holder.iLayoutParent.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                listener.onItemClicked(story)
            }
        })
    }

    override fun getItemCount(): Int {
        return data.size
    }
}