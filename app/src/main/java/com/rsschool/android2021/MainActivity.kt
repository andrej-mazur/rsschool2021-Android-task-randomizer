package com.rsschool.android2021

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity(),
    FirstFragmentStarter,
    SecondFragmentStarter {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startFirstFragment(0)
    }

    override fun startFirstFragment(previousNumber: Int) {
        val firstFragment: Fragment = FirstFragment.newInstance(previousNumber)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, firstFragment)
            .commit()
    }

    override fun startSecondFragment(min: Int, max: Int) {
        val firstFragment: Fragment = SecondFragment.newInstance(min, max)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, firstFragment)
            .commit()
    }
}