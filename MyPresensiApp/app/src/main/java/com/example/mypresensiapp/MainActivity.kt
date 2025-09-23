package com.example.mypresensiapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mypresensiapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

// Implement interface dari LoginFragment
class MainActivity : AppCompatActivity(), OnLoginSuccessListener {

    private lateinit var binding: ActivityMainBinding
    private var isLoggedIn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Toolbar
        setSupportActionBar(findViewById(R.id.toolbar)) // Asumsi Anda punya Toolbar dengan id 'toolbar' di activity_main.xml

        setupTabs()
    }

    private fun setupTabs() {
        binding.viewPager.visibility = View.VISIBLE
        binding.tabLayout.visibility = View.VISIBLE

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        binding.viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Register"
                1 -> "Login"
                else -> ""
            }
        }.attach()
    }

    // Fungsi ini akan dipanggil dari LoginFragment
    override fun onLoginSuccess() {
        isLoggedIn = true
        invalidateOptionsMenu() // Memuat ulang menu agar tombol logout muncul

        // Sembunyikan tabs dan viewpager
        binding.tabLayout.visibility = View.GONE
        binding.viewPager.visibility = View.GONE

        // Tampilkan WelcomeFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, WelcomeFragment()) // R.id.main adalah id dari root layout di activity_main.xml
            .commit()
    }

    // Mengatur Menu di Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        // Dapatkan item menu 'Keluar' yang ID-nya action_home
        val logoutItem = menu?.findItem(R.id.action_home)
        // Tampilkan tombol logout hanya jika sudah login
        logoutItem?.isVisible = isLoggedIn
        return true
    }

    // Mengatur aksi klik pada menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_home -> { // ID dari menu_options.xml
                // Lakukan proses logout
                isLoggedIn = false
                invalidateOptionsMenu() // Sembunyikan lagi tombol logout

                // Hapus WelcomeFragment dan tampilkan lagi tabs
                supportFragmentManager.findFragmentById(R.id.main)?.let {
                    supportFragmentManager.beginTransaction().remove(it).commit()
                }
                setupTabs()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}