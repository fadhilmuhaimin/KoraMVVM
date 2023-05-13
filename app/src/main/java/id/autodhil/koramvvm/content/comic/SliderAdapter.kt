package id.autodhil.koramvvm.content.comic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import id.autodhil.koramvvm.R

class SliderAdapter(val mSliderItems : List<Int>) : SliderViewAdapter<SliderAdapter.SliderAdapterVH>() {
    class SliderAdapterVH(val view : View) : SliderViewAdapter.ViewHolder(view) {
         val image = view.findViewById<ImageView>(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        val inflate = LayoutInflater.from(parent?.context).inflate(R.layout.cutom_layout,null)
        return SliderAdapterVH(inflate)
    }

    override fun getCount(): Int {
        return mSliderItems.size
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.itemView?.let {
            Glide.with(it)
                .load(mSliderItems.get(position))
                .fitCenter()
                .into(viewHolder?.image)
        }
    }

}