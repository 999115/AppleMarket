package com.example.applemarket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.applemarket.databinding.ActivityMainpageBinding

class MainpageActivity : AppCompatActivity(), FragmentDataListener {

    private val binding: ActivityMainpageBinding by lazy {
        ActivityMainpageBinding.inflate(layoutInflater)
    }

    private val productData by lazy {
        Intent(this, DetailpageActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(MainFirstFragment())
        binding.ivMoveFragment.setOnClickListener {
            setFragment(MainSecondFragment())
        }
        initListener()

    }

    override fun onDataReceived(infoData: ProductInfo) {
        productData.putExtra("infoData", infoData)
        startActivity(productData)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frame_fragment, fragment)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

    private fun initListener() {

        binding.ivMoveFragment.setOnClickListener {
            setFragment(MainSecondFragment())
        }

    }

}