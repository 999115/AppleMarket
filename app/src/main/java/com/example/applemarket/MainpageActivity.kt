package com.example.applemarket

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.applemarket.databinding.ActivityMainpageBinding

class MainpageActivity : AppCompatActivity() {

    private val binding: ActivityMainpageBinding by lazy {
        ActivityMainpageBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(MainFirstFragment())
        binding.ivMoveFragment.setOnClickListener {
            setFragment(MainSecondFragment())
        }

    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frame_scrollview, fragment)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

}