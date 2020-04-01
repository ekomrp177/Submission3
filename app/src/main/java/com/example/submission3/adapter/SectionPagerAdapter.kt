package com.example.submission3.adapter

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.*
import com.example.submission3.R
import com.example.submission3.fragment.MovieFragment
import com.example.submission3.fragment.TvshowFragment

class SectionPagerAdapter(private val mContext : Context, fm : FragmentManager): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TAB_TITLES = intArrayOf(R.string.tabLayout1, R.string.tabLayout2)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position){
            0 -> fragment = MovieFragment()
            1 -> fragment = TvshowFragment()
        }
        return fragment as Fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLES[position])
    }
}