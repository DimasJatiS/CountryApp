// File: SectionPagerAdapter.kt
package com.example.tugasmenggila

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

// Pemanggilan ViewPager
class SectionPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RegisterFragment()
            1 -> LoginFragment()
            else -> throw IllegalStateException("Invalid position $position")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}