package com.example.applemarket

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private val binding: FragmentFirstBinding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }

    @SuppressLint("DiscouragedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val adapter = DefaultAdapter(ProductListData.dummyData)
        binding.recyclerviewMain.adapter = adapter
        binding.recyclerviewMain.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        adapter.click = object : DefaultAdapter.Click {
            override fun clicked(view: View, position: Int) {
                val goDetail = Intent(context, DetailpageActivity::class.java)
                startActivity(goDetail)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

}