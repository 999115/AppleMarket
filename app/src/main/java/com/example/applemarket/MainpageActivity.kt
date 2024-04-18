package com.example.applemarket

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.applemarket.databinding.ActivityMainpageBinding
import java.text.DecimalFormat

class MainpageActivity : AppCompatActivity(), FragmentDataListener {

    private val binding: ActivityMainpageBinding by lazy {
        ActivityMainpageBinding.inflate(layoutInflater)
    }

    private val productDataIntent by lazy {
        Intent(this, DetailpageActivity::class.java)
    }

    private lateinit var randomData: ProductInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(MainFragment.newInstance("first"))
        initListener()
        backPressed()

    }

    // Fragment에서 받아온 데이터 Detailpage로 넘기기
    override fun onDataReceived(infoData: ProductInfo) {
        productDataIntent.putExtra("infoData", infoData)
        startActivity(productDataIntent)
    }

    // Fragment 설정
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frame_fragment, fragment)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

    // setOnClickListener 모음
    private fun initListener() {

        binding.ivMoveFragment.setOnClickListener {
            binding.tvLocation.setText(R.string.main_top_location2)
            setFragment(MainFragment.newInstance("second"))
        }

        binding.ivNotice.setOnClickListener {
            notification()
        }
    }

    // 알림 설정
    private fun notification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        // 버전 예외처리
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channelId",
                "channelName",
                NotificationManager.IMPORTANCE_DEFAULT
            )
                .apply {
                    description = "des"
                    setShowBadge(true)
                    setSound(
                        RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_ALARM)
                            .build()
                    )
                    enableVibration(true)
                }
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, "channelId")

        } else {
            builder = NotificationCompat.Builder(this)
        }

        // intent 설정
        val randomDataIntent = Intent(this, DetailpageActivity::class.java)
        initRandomData()
        randomDataIntent.putExtra("infoData", randomData)
        randomDataIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(
            this, 0, randomDataIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val pictureIcon = BitmapFactory.decodeResource(resources, randomData.image)

        // 알림 구현
        builder.run {
            setSmallIcon(R.drawable.ic_apple)
            setWhen(System.currentTimeMillis())
            setContentTitle(getString(R.string.notice_title))
            setContentText(getString(randomData.title))
//            setStyle(NotificationCompat.BigTextStyle()
//                .bigText(getString(R.string.main_first_subscription1))
//            )
            setLargeIcon(pictureIcon)
            setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(pictureIcon)
                    .bigLargeIcon(null as Icon?)
            )
            addAction(R.drawable.ic_apple, getString(R.string.notice_check), pendingIntent)
        }

        manager.notify(11, builder.build())

    }

    // 뒤로가기 버튼 클릭 시 실행될 함수
    private fun backPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                setDialog()
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    // Dialog 설정
    private fun setDialog() {
        val builder = AlertDialog.Builder(this@MainpageActivity)
        val view = layoutInflater.inflate(R.layout.layout_dialog, null)
        val end = DialogInterface.OnClickListener { _, p0 ->
            if (p0 == DialogInterface.BUTTON_POSITIVE) finish()
        }

        initRandomData()
        view.findViewById<ImageView>(R.id.iv_product_img).setImageResource(randomData.image)
        view.findViewById<TextView>(R.id.tv_title).setText(randomData.title)
        view.findViewById<TextView>(R.id.tv_address).setText(randomData.address)
        view.findViewById<TextView>(R.id.tv_price).text = getString(
            R.string.main_price,
            DecimalFormat("#,###,###").format(getString(randomData.price).toInt())
        )

        builder.setTitle(R.string.dialog_title)
        builder.setIcon(R.drawable.ic_apple)
        builder.setView(view)
        builder.setPositiveButton(R.string.dialog_check, end)
        builder.setNegativeButton(R.string.dialog_cancel, null)
        builder.show()
    }

    // ProductInfo에 랜덤한 값 구현
    private fun initRandomData() {
        val randomNum = (1..10).random()
        val randomStr = if ((1..2).random() == 1) "first" else "second"

        randomData = ProductInfo(
            resources.getIdentifier("img_${randomStr}_${randomNum}", "drawable", "com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_title${randomNum}","string", "com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_subscription${randomNum}","string","com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_seller${randomNum}","string","com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_price${randomNum}","string","com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_address${randomNum}","string","com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_likeNum${randomNum}","string","com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_chatNum${randomNum}","string","com.example.applemarket"),
            resources.getIdentifier("main_${randomStr}_temp${randomNum}","string","com.example.applemarket")
        )
    }

}