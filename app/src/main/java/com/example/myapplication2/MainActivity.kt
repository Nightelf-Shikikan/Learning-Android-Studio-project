package com.example.myapplication2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication2.databinding.ActivityMainBinding
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DynamicViewPagerAdapter(
    fa: FragmentActivity,
    private val fragments: MutableList<Fragment> = mutableListOf(),
    private val titles: MutableList<String> = mutableListOf()
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

    // Optional helper function to add fragments dynamically
    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
        notifyItemInserted(fragments.size - 1)
    }

    fun getTitle(position: Int): String = titles[position]
}

// ---------------- MainActivity -----------------
class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var adapter: DynamicViewPagerAdapter
    private lateinit var tabLayoutMediator: TabLayoutMediator


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        // Initialize adapter with starting fragments
        adapter = DynamicViewPagerAdapter(this)
        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(DetailsFragment(), "Details")
        adapter.addFragment(SettingsFragment(), "Settings")

        viewPager.adapter = adapter
        // Attach tabs to ViewPager2
        tabLayoutMediator = TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTitle(position)
        }
        tabLayoutMediator.attach()

        // Example button to add fragment at runtime
        val button = findViewById<Button>(R.id.btnAddFragment)
        button.setOnClickListener {
            adapter.addFragment(NewFragment(), "New")

            // Re-attach mediator to show new tab
            tabLayoutMediator.detach()
            tabLayoutMediator.attach()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

}