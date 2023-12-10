package com.example.manshatsoultancommunity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeViewPagerAdapter(
    private val fragment:List<Fragment>,
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
):FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return  fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }


}