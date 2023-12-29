package com.example.manshatsoultancommunity.features.news.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.manshatsoultancommunity.R
import com.example.manshatsoultancommunity.features.news.presentation.common.adapter.HomeViewPagerAdapter
import com.example.manshatsoultancommunity.databinding.FragmentHomeBinding
import com.example.manshatsoultancommunity.features.news.presentation.common.ViewModels.PostViewModel
import com.example.manshatsoultancommunity.util.Resource
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint

class HomeFragment: Fragment() {
    private lateinit var binding : FragmentHomeBinding
    private val viewModel by viewModels<PostViewModel>()

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
                1 -> tab.text = "وفــيات"
                2 -> tab.text = "ريــاضة"
                3 -> tab.text = "تـعليم"
            }
        }.attach()
        lifecycleScope.launch {
            viewModel.generalPostList.collectLatest {
                when(it){
                    is Resource.Success -> {
                        viewModel.fetchPosts()
                        val count = it.data?.size ?: 0
                        val badge: BadgeDrawable? = binding.tabLayout.getTabAt(0)?.orCreateBadge
                        badge?.apply {
                            isVisible = true
                            number = count
                            backgroundColor = resources.getColor(R.color.title)
                        }
                    }
                    else -> Unit
                }
            }
        }



        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    if (tab.position == viewPager2Adapter.itemCount - 1) {
                        if (binding.tabLayout.selectedTabPosition != 0) {

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