package com.example.myapplicationdemo

import ArticleAdapter
import ArticleResponse
import android.content.Intent
import android.os.Bundle
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

    sendRequest()
  }

  private fun sendRequest() {

    val url = "https://www.wanandroid.com/article/list/0/json"
    val request = okhttp3.Request.Builder()
      .url(url)
      .build()

    val client = OkHttpClient()
    client.newCall(request).enqueue(object : Callback {
      override fun onResponse(call: Call, response: Response) {
        val responseData = response.body?.string()
        val gson = Gson()
        val articleResponse = gson.fromJson(responseData, ArticleResponse::class.java)

        runOnUiThread {
          adapter.setData(articleResponse.data.datas)
        }
      }

      override fun onFailure(call: Call, e: IOException) {
        e.printStackTrace()
      }
    })
  }

}