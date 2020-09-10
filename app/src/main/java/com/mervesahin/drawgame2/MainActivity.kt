package com.mervesahin.drawgame2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private val logTAG="MainActLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Thread(Runnable {
            run{
                try{
                    Thread.sleep(3000)
                } catch (e:Exception) {
                    Log.d(logTAG,"Hata : ${e.message}")
                }
                val intent = Intent(this, SelectImageActivity::class.java)
                startActivity(intent)
            }
        }).start()
    }
}
