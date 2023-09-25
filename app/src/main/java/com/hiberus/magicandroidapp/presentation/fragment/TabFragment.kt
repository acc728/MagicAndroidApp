package com.hiberus.magicandroidapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.hiberus.magicandroidapp.R
import com.hiberus.magicandroidapp.databinding.FragmentTabBinding

class TabFragment : Fragment() {
    private var _binding: FragmentTabBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initUI() {

        val tabLayout = binding.tlMagicTabs
        val viewPager = binding.vp2MagicViewpager

        viewPager.adapter = MagicViewPagerAdapter(this)


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.search_cards)
                else -> getString(R.string.collection_cards)
            }
        }.attach()
    }

    private inner class MagicViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        override fun getItemCount() = 2
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> CardSearchFragment()
                1 -> CollectionFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }
    }
}