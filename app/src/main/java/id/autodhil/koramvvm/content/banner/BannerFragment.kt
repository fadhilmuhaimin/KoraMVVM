package id.autodhil.koramvvm.content.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import id.autodhil.koramvvm.R


class BannerFragment : Fragment(){
    private lateinit var  image : ImageView

    companion object{
        @JvmStatic
        fun newInstance(url : Int) : BannerFragment{
            val comicFragment = BannerFragment()
            val args = Bundle()
            args.putInt("img",url)
            comicFragment.arguments = args
            return comicFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.cutom_layout,container,false)
        image = v.findViewById<ImageView>(R.id.image_view)

        return v

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getInt("img")
        context?.let {
            Glide.with(it)
                .load(url)
                .into(image)
        }
    }
}