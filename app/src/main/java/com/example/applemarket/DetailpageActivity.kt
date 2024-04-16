package com.example.applemarket

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initListener()
    }

    private fun initData() {

//        tvWriter.text = intent.getStringExtra("writer_key") ?: "작성자"
//        tvPost.text = intent.getStringExtra("content_key") ?: "전체 게시글 내용"

    }

    private fun initListener() {

//        var numLike = getString(R.string.str_like).toInt() // 기본 좋아요 숫자를 string.xml에서 관리하도록 했습니다.
//        var numDislike = getString(R.string.str_dislike).toInt()
//
//        btLike.setOnClickListener {
//            numLike++
//            tvLike.text = "$numLike"
//            Toast.makeText(this, getString(R.string.str_toast_like), Toast.LENGTH_SHORT).show()
//        }
//
//        btBack.setOnClickListener {
//            finish()
//            applyAnimationClose(R.anim.none_enter, R.anim.slide_left_exit)
//        }

    }
}