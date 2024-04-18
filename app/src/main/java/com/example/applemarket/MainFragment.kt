package com.example.applemarket

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.FragmentMainBinding
import java.lang.RuntimeException

class MainFragment : Fragment() {

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val productListData by lazy {
        mutableListOf<ProductInfo>()
    }

    private var listener: FragmentDataListener? = null

    private var indexData: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is FragmentDataListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement FragmentDataListener")
        }
    }

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        indexData = arguments?.getString(ARG_DATA)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProductListData(indexData)
        val adapter = MainDefaultAdapter(productListData)

        binding.recyclerviewMain.apply {
            addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            layoutManager =
                LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            this.adapter = adapter
        }

        adapter.click = object : MainDefaultAdapter.Click {
            override fun clicked(view: View, position: Int) {
                val selectedData = productListData[position]
                listener?.onDataReceived(selectedData)
            }
        }

    }

    @SuppressLint("DiscouragedApi")
    fun initProductListData(setDataIndex: String?) {
        for (i in 1..10) {
            productListData.add(
                ProductInfo(
                    resources.getIdentifier("img_${setDataIndex}_${i}", "drawable", "com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_title${i}","string", "com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_subscription${i}","string","com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_seller${i}","string","com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_price${i}","string","com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_address${i}","string","com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_likeNum${i}","string","com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_chatNum${i}","string","com.example.applemarket"),
                    resources.getIdentifier("main_${setDataIndex}_temp${i}","string","com.example.applemarket")
                )
            )
        }
    }

    companion object {
        val ARG_DATA = "indexData"
        @JvmStatic
        fun newInstance(indexData: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DATA, indexData)
                }
            }
    }

}