package id.autodhil.koramvvm.content.banner

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

@Suppress("DEPRECATION")
class BannerAdapter (fragmentManager: FragmentManager,
                     private val banners : List<BannerComic>) : FragmentPagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        return BannerFragment.newInstance(banners[position].image)
    }

    override fun getCount(): Int {
        return banners.size
    }


}