package id.autodhil.koramvvm
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide


class ViewPagerAdapter (private  val context: Context, private val array : ArrayList<Int>):
    PagerAdapter(){
    private var layoutInflater : LayoutInflater?=null
//    private var images = arrayOf(R.drawable.animation,R.drawable.animation2,R.drawable.animation3,R.drawable.animation4)

    private var  custom_position : Int = 0;

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (custom_position > 4)
            custom_position = 0
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = layoutInflater!!.inflate(R.layout.cutom_layout,null)
        val image = v.findViewById<View>(R.id.image_view) as ImageView
        Glide.with(context)
            .load(array[custom_position])
            .into(image)
        custom_position++
        val vp = container as ViewPager
        vp.addView(v,0)
        return v
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val v = `object` as View
        vp.removeView(v)
    }
}