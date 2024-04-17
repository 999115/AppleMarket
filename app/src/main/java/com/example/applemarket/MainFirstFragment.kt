package com.example.applemarket

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.FragmentMainFirstBinding
import java.lang.RuntimeException

class MainFirstFragment : Fragment() {

    private val binding: FragmentMainFirstBinding by lazy {
        FragmentMainFirstBinding.inflate(layoutInflater)
    }

    private val productListData by lazy {
        mutableListOf<ProductInfo>()
    }

    private var listener: FragmentDataListener? = null

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

        initProductListData()
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("DiscouragedApi")
    fun initProductListData() {
        for (i in 1..10) {
            productListData.add(
                ProductInfo(
                    resources.getIdentifier("sample${i}", "drawable", "com.example.applemarket"),
                    resources.getIdentifier("main_first_title${i}", "string", "com.example.applemarket"),
                    resources.getIdentifier("main_first_subscription${i}", "string", "com.example.applemarket"),
                    resources.getIdentifier("main_first_seller${i}", "string", "com.example.applemarket"),
                    resources.getIdentifier("main_first_price${i}", "string", "com.example.applemarket"),
                    resources.getIdentifier("main_first_address${i}", "string", "com.example.applemarket"),
                    resources.getIdentifier("main_first_likeNum${i}", "string", "com.example.applemarket"),
                    resources.getIdentifier("main_first_chatNum${i}", "string", "com.example.applemarket")
                )
            )
        }
    }

}