package com.mervesahin.drawgame2

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class ImageAdapter(var drawModel:DrawModel , var mContext: Context): Item<GroupieViewHolder>() {
    override fun getLayout(): Int {
        return R.layout.image_card_design
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val view = viewHolder.itemView

        val image_background = view.findViewById<ImageView>(R.id.taslakGorseli)
        Glide.with(mContext).load(drawModel.taslak_image).into(image_background)

    }
}