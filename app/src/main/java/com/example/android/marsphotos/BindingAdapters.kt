package com.example.android.marsphotos

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

class BindingAdapters {

    @BindingAdapter("imageUrl") // imageUrl 속성이 있는 경우 이 결합 어댑터를 실행하도록 데이터 결합에 지시
    fun bindImage(imgView: ImageView, imgUrl: String?){
        imgUrl?.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri){
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
            }
        }
    }
}