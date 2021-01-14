package com.mohammedsendi.senators

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() , SenatorsListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = SenatorsListFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onSenatorClick(bioId: String) {
        val fragment = SenatorDetailsFragment.newInstance(bioId)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,fragment)
            .addToBackStack(null)
            .commit()

    }
}