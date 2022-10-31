package com.example.mystoryappdicoding.view.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mystoryappdicoding.R
import com.example.mystoryappdicoding.data.remote.dto.Story

class StoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story)

        setupData()
    }

    private fun setupData() {
        val story = intent.getParcelableExtra<Story>("Story") as Story
        Glide.with(applicationContext)
            .load(story.photoUrl)
            .circleCrop()
            .into(findViewById(R.id.profileImageView))
        findViewById<TextView>(R.id.nameTextView).text = story.name
        findViewById<TextView>(R.id.descTextView).text = story.description
    }
}