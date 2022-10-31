package com.example.mystoryappdicoding.view.main

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystoryappdicoding.R
import com.example.mystoryappdicoding.data.remote.dto.Story
import com.example.mystoryappdicoding.view.story.StoryActivity

class StoryAdapter :
    PagingDataAdapter<Story, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean =
                oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? StoryViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoryViewHolder.getInstance(parent)
    }


    class StoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun getInstance(parent: ViewGroup): StoryViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.story_item, parent, false)
                return StoryViewHolder(view)
            }
        }
        private var imageView: ImageView = view.findViewById(R.id.iv_item_photo)
        private var author: TextView = view.findViewById(R.id.tv_item_name)


        fun bind(item: Story?) {
            Glide.with(imageView).load(item?.photoUrl.orEmpty()).into(imageView)
            author.text = item?.name

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, StoryActivity::class.java)
                intent.putExtra("Story", item)

                val optionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(
                        itemView.context as Activity,
                        androidx.core.util.Pair(imageView,"profile"),
                        androidx.core.util.Pair(author, "name")
                )
                itemView.context.startActivity(intent, optionsCompat.toBundle())
            }
        }

    }


}