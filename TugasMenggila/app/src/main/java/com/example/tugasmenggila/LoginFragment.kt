// File: LoginFragment.kt
package com.example.tugasmenggila

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mwwnghubungkan view dari layout
        val usernameEditText: EditText = view.findViewById(R.id.edt_username_login)
        val passwordEditText: EditText = view.findViewById(R.id.edt_password_login)
        val loginButton: Button = view.findViewById(R.id.btn_login)

        loginButton.setOnClickListener {
            // Ambil data input
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Reset error
            usernameEditText.error = null
            passwordEditText.error = null

            // Membuat pesan validasi
            var isValid = true
            if (username.isEmpty()) {
                usernameEditText.error = "Username tidak boleh kosong"
                isValid = false
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Password tidak boleh kosong"
                isValid = false
            }

            // navigasi ke halaman home jika valid
            if (isValid) {
                // Simpan status login
                val session = SessionManager(requireContext())
                session.setLoggedIn(true)

                // Pindah halaman
                (activity as? MainActivity)?.navigateToHome(username)
            }
        }
    }
}