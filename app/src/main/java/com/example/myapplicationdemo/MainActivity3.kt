package com.example.myapplicationdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity3 : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main3)

    //设置Activity3的标题
    supportActionBar?.title = "This is Activity3"

    //获取底部导航栏
    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
    //设置导航栏选项点击事件监听器
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
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
  }
}