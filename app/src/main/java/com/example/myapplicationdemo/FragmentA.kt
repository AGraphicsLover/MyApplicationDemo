package com.example.myapplicationdemo

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentA.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentA : Fragment() {
  // TODO: Rename and change types of parameters
  private var param1: String? = null
  private var param2: String? = null

  private var isText2 = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
      param1 = it.getString(ARG_PARAM1)
      param2 = it.getString(ARG_PARAM2)
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    val view = inflater.inflate(R.layout.fragment_a, container, false)

    val imageView = view.findViewById<ImageView>(R.id.gif_Pic)
    //加载GIF图片
    Glide.with(this)
      .asGif()
      .load(R.drawable.my_gif1)
      .into(imageView)

    val textView1 = view.findViewById<TextView>(R.id.textFragmentA1)

    //设置长按点击监听器
    textView1.setOnLongClickListener {
      Toast.makeText(context, "你怎么知道可以长按文本？", Toast.LENGTH_SHORT).show()
      true
    }

    val textView2 = view.findViewById<TextView>(R.id.textFragmentA2)

    //设置点击监听器
    textView2.setOnClickListener {
      if (isText2) {
        textView2.setText(R.string.myString7)
      } else {
        textView2.setText(R.string.myString6)
      }
      isText2 = !isText2
      Toast.makeText(context, "你怎么知道可以改变文本？", Toast.LENGTH_SHORT).show()
    }

    //找到按钮
    val showDialogButton = view.findViewById<Button>(R.id.show_dialog_button)

    //设置点击事件监听器
    showDialogButton.setOnClickListener {
      val alertDialogBuilder = AlertDialog.Builder(requireContext())
      alertDialogBuilder.setTitle("提示")
      alertDialogBuilder.setMessage("小可爱真听话！")
      alertDialogBuilder.setPositiveButton("确定") { dialog, _ ->
        dialog.dismiss()
      }
      val alertDialog = alertDialogBuilder.create()
      alertDialog.show()
    }
    return view
  }

  companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentA.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      FragmentA().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}