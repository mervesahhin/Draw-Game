package com.mervesahin.drawgame2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.OnItemClickListener
import kotlinx.android.synthetic.main.activity_select_image.*
import java.util.*

class SelectImageActivity : AppCompatActivity() {

    private val logTAG:String="SelectImageLog"
    var db: FirebaseFirestore? = null
    private val taslakArrayList: ArrayList<DrawModel> = ArrayList<DrawModel>()
    val adapter: GroupAdapter<*> = GroupAdapter<GroupieViewHolder>()

    val drawModel=DrawModel("","")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_image)


        recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )

        db = FirebaseFirestore.getInstance()
        recyclerView.adapter=adapter

        fetchTaslak()

        adapter.setOnItemClickListener(OnItemClickListener { item, view ->
            val imageAdapter = item as ImageAdapter
            val intent = Intent(this,DrawPageActivity::class.java)

            intent.putExtra("taslakImage",item.drawModel.taslak_image)
            intent.putExtra("taslakID", item.drawModel.taslak_id)

            Log.d(logTAG, "${item.drawModel.taslak_image} ${item.drawModel.taslak_id}")

            startActivity(intent)
        })
    }

    private fun fetchTaslak() {
        db!!.collection("taslak")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                for (doc in task.result!!) {
                    val taslak_id = doc.getString("taslak_id")
                    val taslak_image = doc.getString("taslak_image")
                    val drawModel = DrawModel(taslak_id, taslak_image)
                    adapter.add(ImageAdapter(drawModel,this))
                    Log.d(logTAG, "$taslak_id $taslak_image")
                }
                adapter.notifyDataSetChanged()
            })
            .addOnFailureListener(OnFailureListener { e -> Log.d(logTAG, e.message.toString()) })
    }
}




