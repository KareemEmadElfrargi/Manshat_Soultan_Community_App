package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.manshatsoultancommunity.HomeViewPagerAdapter
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.databinding.FragmentAdsBinding
import com.example.manshatsoultancommunity.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class HomeFragment: Fragment() {
    private lateinit var binding : FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesFragments = arrayListOf(
            GeneralFragment(),
            RIPFragment(),
            SportFragment(),
            EducationFragment(), /*More*/
        )
        val viewPager2Adapter = HomeViewPagerAdapter(categoriesFragments,childFragmentManager,lifecycle)
        binding.viewPager2Home.adapter = viewPager2Adapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager2Home) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_general)
                1 -> tab.text = "صفحة الوفيات"
                2 -> tab.text = "مركز شباب"
                3 -> tab.text = "التــعليم"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    if (tab.position == viewPager2Adapter.itemCount - 1) {
                        if (binding.tabLayout.selectedTabPosition != 0) {
                            //Toast.makeText(requireContext(),"",Toast.LENGTH_SHORT).show()
                            //binding.tabLayout.getTabAt(0)?.select()
                        }
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
}