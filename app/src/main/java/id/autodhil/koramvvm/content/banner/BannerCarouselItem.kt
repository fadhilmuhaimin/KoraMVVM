//package com.autodhil.koramvvm.content.banner
//
//import android.content.ClipData
//
//import androidx.fragment.app.FragmentManager
//import com.autodhil.koramvvm.R
//import com.xwray.groupie.Item
//import com.xwray.groupie.ViewHolder
//import kotlinx.android.synthetic.main.fragment_comic.view.*
//
//
//class BannerCarouselItem(private val banners: List<BannerComic>,
//                             private val supportFragmentManager: FragmentManager
//                             ) : Item<ViewHolder>() {
//    override fun getLayout(): Int {
//        return R.layout.fragment_comic
//    }
//
//    override fun bind(viewHolder: ViewHolder, position: Int) {
//        val viewPagerAdapter = BannerAdapter(supportFragmentManager,banners)
//        viewHolder.itemView.viewpager.adapter = viewPagerAdapter
//        viewHolder.itemView.indicator.setViewPager(viewHolder.itemView.viewpager)
//
//    }
//}