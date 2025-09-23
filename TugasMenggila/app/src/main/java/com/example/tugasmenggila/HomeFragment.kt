// File: HomeFragment.kt
package com.example.tugasmenggila

import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment


class HomeFragment : Fragment() {

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Menu Fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        val toolbar: Toolbar = view.findViewById(R.id.toolbar_home)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        val welcomeTextView: TextView = view.findViewById(R.id.tv_welcome_message)
        welcomeTextView.text = "Welcome, $username!"
    }

    // Metode untuk membuat menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    // Metode untuk tombol yang dapat diklik pada setiap item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val mainActivity = activity as? MainActivity
        return when (item.itemId) {
            R.id.action_logout -> {
                mainActivity?.handleLogout() // Fungsi Logout dipanggil
                true
            }
            R.id.action_exit -> {
                mainActivity?.handleExitApp() // Fungsi Exit dipanggil
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Companion object
    companion object {
        private const val ARG_USERNAME = "arg_username"
        @JvmStatic
        fun newInstance(username: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                }
            }
    }
}