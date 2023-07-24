package com.example.myapplicationdemo

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ArticleData(
  val id: Int,
  val title: String,
  val author: String,
  val link: String,
  val publishTime: Long
) {
  fun getFormattedPublishTime(): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val date = Date(publishTime)
    return dateFormat.format(date)
  }
}