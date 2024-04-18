package com.example.applemarket

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.databinding.ActivityChatpageBinding
import java.text.DecimalFormat

class ChatpageActivity : AppCompatActivity() {

    private val binding: ActivityChatpageBinding by lazy {
        ActivityChatpageBinding.inflate(layoutInflater)
    }

    private lateinit var chatData: ProductInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initListener()

    }

    private fun initData() {

        chatData = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("chatData", ProductInfo::class.java)!!
        } else {
            (intent.getParcelableExtra("chatData") as? ProductInfo)!!
        }

        binding.ivProductImg.setImageResource(chatData.image)
        binding.tvSeller.setText(chatData.seller)
        binding.tvAddress.setText(chatData.address)
        binding.tvTitle.setText(chatData.title)
        binding.tvPrice.text = getString(
            R.string.main_price,
            DecimalFormat("#,###,###").format(getString(chatData.price).toInt())
        )

    }

    private fun initListener() {

        binding.ivSend.setOnClickListener {
            Toast.makeText(this, getString(R.string.detail_chat), Toast.LENGTH_SHORT).show()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

    }

}