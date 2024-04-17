package com.example.applemarket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.applemarket.databinding.ActivityDetailpageBinding
import com.example.applemarket.databinding.ActivityMainpageBinding

class DetailpageActivity : AppCompatActivity() {

    private val binding: ActivityDetailpageBinding by lazy {
        ActivityDetailpageBinding.inflate(layoutInflater)
    }

    private lateinit var infoData: ProductInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initListener()
    }

    private fun initData() {

        infoData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("infoData", ProductInfo::class.java)!!
        } else {
            (intent.getParcelableExtra("infoData") as? ProductInfo)!!
        }

        binding.ivProductImg.setImageResource(infoData.image)
        binding.tvSeller.setText(infoData.seller)
        binding.tvAddress.setText(infoData.address)
        binding.tvTitle.setText(infoData.title)
        binding.tvSubscription.setText(infoData.subscription)
        binding.tvPrice.text = getString(
            R.string.main_price,
            getString(infoData.price).toInt()
        )

    }

    private fun initListener() {

        binding.ivLike.setOnClickListener {
//            numLike++
//            tvLike.text = "$numLike"
            Toast.makeText(this, getString(R.string.detail_toast_like), Toast.LENGTH_SHORT).show()
        }

        binding.btChat.setOnClickListener {
            val chatData = Intent(this, ChatpageActivity::class.java)

            chatData.putExtra("sellerData", binding.tvSeller.text.toString())

            startActivity(chatData)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

    }
}