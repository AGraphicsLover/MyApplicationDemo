package com.example.myapplicationdemo

import ArticleAdapter
import ArticleResponse
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class MainActivity3 : AppCompatActivity() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var adapter: ArticleAdapter
//  private lateinit var progressBar: ProgressBar

  private var currentPage = 0
  private var isLoading = false
  private var isLastPage = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main3)

    //设置Activity3的标题
    supportActionBar?.title = "This is Activity3"

    //获取底部导航栏
    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
    //设置导航栏选项点击事件监听器
    bottomNavigationView.setOnItemSelectedListener { item ->
      when (item.itemId) {
        R.id.action_activity1 -> {
          val intent = Intent(this, MainActivity::class.java)
          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
          startActivity(intent)
          true
        }

        R.id.action_activity2 -> {
          val intent = Intent(this, MainActivity2::class.java)
          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
          startActivity(intent)
          true
        }

        R.id.action_activity3 -> {
          //startActivity(Intent(this@MainActivity3, MainActivity3::class.java))
          true
        }

        else -> false
      }
    }

    //设置底部导航栏选中状态为Activity3
    bottomNavigationView.selectedItemId = R.id.action_activity3

    //对应适配
    recyclerView = findViewById(R.id.recycler_view)
    recyclerView.layoutManager = LinearLayoutManager(this)
    adapter = ArticleAdapter(ArrayList())
    recyclerView.adapter = adapter
//    progressBar = findViewById(R.id.progress_bar)

    //添加滚动监听器
    recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
      override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
        val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
        val totalItemCount = layoutManager.itemCount

        if (!isLoading && !isLastPage && lastVisibleItemPosition == totalItemCount - 1) {
          currentPage++
          loadMoreArticles()
        }
      }
    })

    sendRequest()
  }

  private fun sendRequest() {
//    showProgressBar()

    val url = "https://www.wanandroid.com/article/list/$currentPage/json"
    val request = okhttp3.Request.Builder()
      .url(url)
      .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
      override fun onResponse(call: Call, response: Response) {
//        hideProgressBar()

        val responseData = response.body?.string()
        val gson = Gson()
        val articleResponse = gson.fromJson(responseData, ArticleResponse::class.java)

        runOnUiThread {
          adapter.setData(articleResponse.data.datas)
        }
      }

      override fun onFailure(call: Call, e: IOException) {
//        hideProgressBar()
        e.printStackTrace()
        runOnUiThread {
          showErrorToast()
        }
      }
    })
  }

  private fun loadMoreArticles() {
    isLoading = true
//    showProgressBar()

    val url = "https://www.wanandroid.com/article/list/$currentPage/json"
    val request = okhttp3.Request.Builder()
      .url(url)
      .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
      override fun onResponse(call: Call, response: Response) {
        isLoading = false
//        hideProgressBar()

        val responseData = response.body?.string()
        val gson = Gson()
        val articleResponse = gson.fromJson(responseData, ArticleResponse::class.java)

        runOnUiThread {
          if (articleResponse.data.datas.isNotEmpty()) {
            adapter.addArticles(articleResponse.data.datas)
          } else {
            isLastPage = true
            Toast.makeText(this@MainActivity3, "没有更多数据了！", Toast.LENGTH_SHORT).show()
          }
        }
      }

      override fun onFailure(call: Call, e: IOException) {
        isLoading = false
//        hideProgressBar()
        e.printStackTrace()
        runOnUiThread {
          showErrorToast()
        }
      }

    })
  }

//  private fun showProgressBar() {
//    progressBar.visibility = View.VISIBLE
//  }
//
//  private fun hideProgressBar() {
//    progressBar.visibility = View.GONE
//  }

  private fun showErrorToast() {
    Toast.makeText(this@MainActivity3, "网络请求失败！", Toast.LENGTH_SHORT).show()
  }

}