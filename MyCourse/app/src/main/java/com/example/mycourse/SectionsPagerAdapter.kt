package com.example.mycourse


import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun createFragment(position: Int): androidx.fragment.app.Fragment {
        var fragment: androidx.fragment.app.Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = QuizFragment()
        }
        return fragment as androidx.fragment.app.Fragment
    }

    override fun getItemCount(): Int {
        return 2
    }

}