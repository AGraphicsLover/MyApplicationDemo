package com.example.myapplicationdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentB.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentB : Fragment() {
  // TODO: Rename and change types of parameters
  private var param1: String? = null
  private var param2: String? = null

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
    return inflater.inflate(R.layout.fragment_b, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    //初始化dataList数据
    val dataList = mutableListOf<Pair<String, String>>()
    for (i in 0..50) {
      dataList.add(Pair("书名：Book$i", "这本书讲述了..."))
    }
    //找到RecyclerView
    val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    //设置布局管理器给RecyclerView
    recyclerView.layoutManager = LinearLayoutManager(requireContext())
    //设置Adapter给RecyclerView
    val myAdapter = MyAdapter(dataList)
    recyclerView.adapter = myAdapter

    //设置点击事件的回调函数
    myAdapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
      override fun onItemClick(data: Pair<String, String>) {
        Toast.makeText(requireContext(), "你点击了标题：${data.first}", Toast.LENGTH_SHORT).show()
      }
    })
  }

  companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentB.
     */
    // TODO: Rename and change types and number of parameters
    @JvmStatic
    fun newInstance(param1: String, param2: String) =
      FragmentB().apply {
        arguments = Bundle().apply {
          putString(ARG_PARAM1, param1)
          putString(ARG_PARAM2, param2)
        }
      }
  }
}