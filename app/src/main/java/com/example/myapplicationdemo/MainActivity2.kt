package com.example.myapplicationdemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {

  private lateinit var linearLayoutView: View
  private lateinit var relativeLayoutView: View
  private lateinit var constraintLayoutView: View

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main2)

    //设置Activity2的标题
    supportActionBar?.title = "This is Activity2"

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
          //startActivity(Intent(this@MainActivity2, MainActivity2::class.java))
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

    //设置底部导航栏选中状态为Activity2
    bottomNavigationView.selectedItemId = R.id.action_activity2

    linearLayoutView = findViewById(R.id.linear_layout_view)
    relativeLayoutView = findViewById(R.id.relative_layout_view)
    constraintLayoutView = findViewById(R.id.constraint_layout_view)

  }

  fun showLinearLayout(view: View) {
    linearLayoutView.visibility = View.VISIBLE
    relativeLayoutView.visibility = View.GONE
    constraintLayoutView.visibility = View.GONE
  }

  fun showRelativeLayout(view: View) {
    linearLayoutView.visibility = View.GONE
    relativeLayoutView.visibility = View.VISIBLE
    constraintLayoutView.visibility = View.GONE
  }

  fun showConstraintLayout(view: View) {
    linearLayoutView.visibility = View.GONE
    relativeLayoutView.visibility = View.GONE
    constraintLayoutView.visibility = View.VISIBLE
  }

}