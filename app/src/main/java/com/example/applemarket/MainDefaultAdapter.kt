package com.example.applemarket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.LayoutMainDefaultBinding

class MainDefaultAdapter(private val productList: MutableList<ProductInfo>) :
    RecyclerView.Adapter<MainDefaultAdapter.ViewHolder>() {

    interface Click {
        fun clicked(view: View, position: Int)
    }

    var click : Click? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutMainDefaultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.layout.setOnClickListener {
            click?.clicked(it, position)
        }
        holder.bind(productList[position])
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ViewHolder(private val binding: LayoutMainDefaultBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val layout = binding.layoutProduct
        fun bind(info: ProductInfo) {
            val priceContext = binding.tvPrice.context
            binding.ivProductImg.setImageResource(info.image)
            binding.tvTitle.setText(info.title)
            binding.tvAddress.setText(info.address)
            binding.tvPrice.text = priceContext.getString(
                R.string.main_price,
                priceContext.getString(info.price).toInt()
            )
            binding.tvChatNumber.setText(info.chatNum)
            binding.tvLikeNumber.setText(info.likeNum)
        }
    }
}
