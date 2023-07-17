package com.example.myapplicationdemo

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    //获取底部导航栏
    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
    //设置导航栏选项点击事件监听器
    bottomNavigationView.setOnNavigationItemSelectedListener { item ->
      when (item.itemId) {
        R.id.action_activity1 -> {
          //startActivity(Intent(this@MainActivity, MainActivity::class.java))
          true
        }
        R.id.action_activity2 -> {
          val intent = Intent(this, MainActivity2::class.java)
          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
          startActivity(intent)
          true
        }
        R.id.action_activity3 -> {
          val intent = Intent(this, MainActivity3::class.java)
          intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
          startActivity(intent)
          true
        }
        else -> false
      }
    }

    //设置底部导航栏选中状态为ActivityA
    bottomNavigationView.selectedItemId = R.id.action_activity1

    //默认显示FragmentA
    if (savedInstanceState == null) {
      replaceFragment(FragmentA())
    }
  }

  //处理切换Fragment的方法
  private fun replaceFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
      .replace(R.id.fragment_container, fragment)
      .commit()
  }

  //处理切换Fragment的按钮点击事件
  fun switchFragments(view: View) {
    val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

    if(currentFragment is FragmentA) {
      replaceFragment(FragmentB())
    } else if (currentFragment is FragmentB) {
      replaceFragment(FragmentA())
    }
  }
}