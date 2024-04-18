package com.example.applemarket

import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.applemarket.databinding.ActivityDetailpageBinding
import java.text.DecimalFormat

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

        val temp = getString(infoData.temp).toDouble()
        var colorId = 0
        var imgId = 0

        when {
            temp >= 0 && temp < 25 -> {
                colorId = R.color.red
                imgId = R.drawable.img_6
            }
            temp >= 25 && temp < 30 -> {
                colorId = R.color.apple
                imgId = R.drawable.img_5
            }
            temp >= 30 && temp < 33 -> {
                colorId = R.color.orange
                imgId = R.drawable.img_4
            }
            temp >= 33 && temp < 35 -> {
                colorId = R.color.yellow
                imgId = R.drawable.img_3
            }
            temp >= 35 && temp < 37 -> {
                colorId = R.color.green
                imgId = R.drawable.img_2
            }
            else -> {
                colorId = R.color.emerald
                imgId = R.drawable.img_1
            }
        }

        binding.ivProductImg.setImageResource(infoData.image)
        binding.tvSeller.setText(infoData.seller)
        binding.tvAddress.setText(infoData.address)
        binding.tvTitle.setText(infoData.title)
        binding.tvSubscription.setText(infoData.subscription)
        binding.tvPrice.text = getString(
            R.string.main_price,
            DecimalFormat("#,###,###").format(getString(infoData.price).toInt())
        )
        binding.tvTempDegree.text = getString(
            R.string.detail_temp_degree,
            DecimalFormat("##.#").format(temp)
        )
        binding.tvTempDegree.setTextColor(ResourcesCompat.getColor(getResources(), colorId, null))
        binding.ivTempImg.setImageResource(imgId)

    }

    private fun initListener() {

        binding.ivLike.setOnClickListener {
//            numLike++
//            tvLike.text = "$numLike"
            Toast.makeText(this, getString(R.string.detail_toast_like), Toast.LENGTH_SHORT).show()
        }

        binding.btChat.setOnClickListener {
            val chatData = Intent(this, ChatpageActivity::class.java)

            chatData.putExtra("chatData", infoData)

            startActivity(chatData)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

    }

}