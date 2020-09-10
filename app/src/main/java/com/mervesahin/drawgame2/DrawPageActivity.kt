package com.mervesahin.drawgame2

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.Color
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_draw_page.*

class DrawPageActivity : AppCompatActivity() {
    private lateinit var mp : MediaPlayer
    private var totalTime: Int = 0
    private var logTAG: String="DrawPageLog"

    val REQUEST_CODE_DRAW=15
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw_page)

        val imageUrl=intent.getStringExtra("taslakImage")
        intent.getStringExtra("taslakID")
        Glide.with(this).load(imageUrl).into(image_background)



        if(imageUrl == "https://firebasestorage.googleapis.com/v0/b/kidsgame-40528.appspot.com/o/baykus2.jpg?alt=media&token=e786d9fb-3c84-4e05-a868-89ffb79286a7"){
            mp = MediaPlayer.create(this, R.raw.baykus)
            mp.isLooping = true
            mp.setVolume(0.5f,0.5f)
            totalTime = mp.duration
        }
        if(imageUrl == "https://firebasestorage.googleapis.com/v0/b/kidsgame-40528.appspot.com/o/aslan2.jpg?alt=media&token=3c76bef0-458f-4b5c-8ce5-ec87c853aebd"){
            mp = MediaPlayer.create(this, R.raw.aslan)
            mp.isLooping = true
            mp.setVolume(0.5f,0.5f)
            totalTime = mp.duration
        }
        if(imageUrl == "https://firebasestorage.googleapis.com/v0/b/kidsgame-40528.appspot.com/o/ayi2.jpg?alt=media&token=cce7e131-3468-42df-b115-35aa15b6443e"){
            mp = MediaPlayer.create(this, R.raw.ayi)
            mp.isLooping = true
            mp.setVolume(0.5f,0.5f)
            totalTime = mp.duration
        }
        if(imageUrl == "https://firebasestorage.googleapis.com/v0/b/kidsgame-40528.appspot.com/o/kedi2.jpg?alt=media&token=9ebfa459-b05c-47fb-9c7b-566a67f18e2f"){
            mp = MediaPlayer.create(this, R.raw.kedi)
            mp.isLooping = true
            mp.setVolume(0.5f,0.5f)
            totalTime = mp.duration
        }
        if(imageUrl == "https://firebasestorage.googleapis.com/v0/b/kidsgame-40528.appspot.com/o/papagan2.jpg?alt=media&token=09294f62-c637-4896-a3e9-4a2b7b0f0b44"){
            mp = MediaPlayer.create(this, R.raw.papagan)
            mp.isLooping = true
            mp.setVolume(0.5f,0.5f)
            totalTime = mp.duration
        }
        if(imageUrl == "https://firebasestorage.googleapis.com/v0/b/kidsgame-40528.appspot.com/o/sincap2.jpg?alt=media&token=fdfabab9-6e46-470a-8a01-70a7bdb8718d"){
            mp = MediaPlayer.create(this, R.raw.sincap)
            mp.isLooping = true
            mp.setVolume(0.5f,0.5f)
            totalTime = mp.duration
        }


    }
    fun Temizleme(view: View){
        draw_view.clearCanvas()
    }
    fun Kalem(view: View){
        draw_view.setStrokeWidth(20F)
    }
    fun Boya(view: View){
        draw_view.setStrokeWidth(50F)
    }
    fun silme(view: View){
        draw_view.setStrokeWidth(20F)
        draw_view.setColor(Color.WHITE)
    }

    fun Gerial(view: View){
        draw_view.undo()
    }

    fun colorClicked(view: View){
        when(view.id){
            R.id.img_button_brown->{
                draw_view.setColor(Color.parseColor("#FF660000"))
            }
            R.id.img_button_red->{
                draw_view.setColor(Color.RED)
            }
            R.id.img_button_orange->{
                draw_view.setColor(Color.parseColor("#FFFF6600"))
            }
            R.id.img_button_yellow->{
                draw_view.setColor(Color.YELLOW)
            }
            R.id.img_button_blue->{
                draw_view.setColor(Color.parseColor("#91CBF8"))
            }
            R.id.img_button_green->{
                draw_view.setColor(Color.GREEN)
            }
            R.id.img_button_darkblue->{
                draw_view.setColor(Color.parseColor("#4157D1"))
            }
            R.id.img_button_pink->{
                draw_view.setColor(Color.parseColor("#F64783"))
            }
            R.id.img_button_white-> {
                draw_view.setColor(Color.WHITE)
            }
            R.id.img_button_purple-> {
                draw_view.setColor(Color.parseColor("#9C27B0"))
            }
            R.id.img_button_black-> {
                draw_view.setColor(Color.BLACK)
            }
        }
    }

    fun Kaydet(view: View){
        val saveDialog = AlertDialog.Builder(this)
        saveDialog.setTitle("Save Drawing")
        saveDialog.setMessage("Save drawing to device gallery?")
        saveDialog.setPositiveButton(
            "Yes"
        ) { dialog, which ->

            val bitmap: Bitmap =draw_view.getBitmap()

            Log.d(logTAG,"Kaydet 1 : "+bitmap.byteCount)

            // Save the image to gallery and get saved image Uri
            val uri = saveImage(bitmap,"Baykus")
            Log.d(logTAG,"$uri")
        }
        saveDialog.setNegativeButton(
            "No"
        ) { dialog, which ->
            dialog.cancel()
        }
        saveDialog.show()
    }

    fun saveImage(bitmap: Bitmap, title:String): Uri {
        Log.d(logTAG,"Kaydet saveImage")

        // Save image to gallery
        val savedImageURL = MediaStore.Images.Media.insertImage(
            contentResolver,
            bitmap,
            title,
            "Image of $title"
        )

        // Parse the gallery image url to uri
        return Uri.parse(savedImageURL)

    }

    fun OkClicked(view: View){
        val ttb = AnimationUtils.loadAnimation(this, R.anim.animeffect)
        image_background.startAnimation(ttb)
        draw_view.startAnimation(ttb)

        if(mp.isPlaying){
            mp.pause()
        }else{
            mp.start()
        }
    }

}
