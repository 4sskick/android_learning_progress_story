package id.niteroomcreation.learningprogressstory.presenter.feature.main.stories.paging_footer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.niteroomcreation.learningprogressstory.R
import id.niteroomcreation.learningprogressstory.databinding.IStoriesFooterBinding
import id.niteroomcreation.learningprogressstory.util.executeWithAction

/**
 * Created by Septian Adi Wijaya on 19/02/2023.
 * please be sure to add credential if you use people's code
 */
class StoriesFooterAdapter : LoadStateAdapter<StoriesFooterAdapter.FooterViewHolder>() {

    class FooterViewHolder(
        private val binding: IStoriesFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun binds(loadState: LoadState) {
            binding.executeWithAction {
                //from <variable> inside <data> on layout
                footerUiState = FooterUiState(loadState)
            }
        }
    }

    override fun onBindViewHolder(holder: FooterViewHolder, loadState: LoadState) {
        holder.binds(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): FooterViewHolder {
        val itemBinding: IStoriesFooterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.i_stories_footer,
            parent,
            false
        )

        return FooterViewHolder(itemBinding)
    }
}