package id.autodhil.koramvvm

import android.graphics.Bitmap
import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition

import com.google.android.material.snackbar.Snackbar
import java.lang.Exception


@BindingAdapter("snackbar")
fun ShowSnackbar(view: View?, message : String?){
    try {
        if (message != null){
            val snackbar =
                Snackbar.make(view ?: throw Exception("No View"),"",Snackbar.LENGTH_LONG)

            val v = snackbar.view

            v.background = ContextCompat.getDrawable(view.context,R.color.colorPrimary)

            val tv = v.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView

            tv.setTextColor(Color.parseColor("#FFFFFF"))
            snackbar.setText(message)
            snackbar.show()
        }
    }catch (e: Exception) {
        Log.e("Error", e.message + " Message Error")
    }
}

@BindingAdapter("visible")
fun setVisibility(view: View?, isVisible: Boolean) {
    if (isVisible)
        view?.visibility = View.VISIBLE
    else
        view?.visibility = View.GONE
}

@BindingAdapter("LoadeImage")
fun loadImage(imgView: ImageView, url: String?){
    Glide.with(imgView.context)
        .asBitmap()//[for new glide versions]
        .load(url) //.asBitmap()[for older glide versions]
        .placeholder(R.drawable.placeholder)
        .override(2000, 2000) // Can be 2000, 2000
        .into(object : BitmapImageViewTarget(imgView) {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                super.onResourceReady(resource, transition)
            }

        })
}

@BindingAdapter("showImage")
fun showImage(imgView: ImageView, url: String?){
    Glide.with(imgView.context)
        .load(url)
        .placeholder(R.drawable.placeholder)
        .into(imgView)
}