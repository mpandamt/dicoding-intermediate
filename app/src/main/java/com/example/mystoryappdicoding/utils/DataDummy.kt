package com.example.mystoryappdicoding.utils

import com.example.mystoryappdicoding.data.remote.dto.Story

object DataDummy {

 fun generateDummyStory(): List<Story> {
  val storyList = ArrayList<Story>()
  for (i in 0..10) {
   val story = Story(
    "2022-02-22 22:22:10",
    "Description $i",
    "iniId$i",
    -6.8668408,
    107.608081,
    "title $i",
    "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/commons/feature-1-kurikulum-global-3.png"
   )
   storyList.add(story)
  }
  return storyList
 }
}
