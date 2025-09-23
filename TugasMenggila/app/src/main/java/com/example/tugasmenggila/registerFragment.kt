// File: RegisterFragment.kt
package com.example.tugasmenggila

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hubungkan semua view dari layout
        val usernameEditText: EditText = view.findViewById(R.id.edt_username)
        val emailEditText: EditText = view.findViewById(R.id.edt_email)
        val phoneEditText: EditText = view.findViewById(R.id.edt_Phone)
        val passwordEditText: EditText = view.findViewById(R.id.edt_pw)
        val registerButton: Button = view.findViewById(R.id.btn_register)

        registerButton.setOnClickListener {
            // Ambil data dari input
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Reset error sebelumnya
            usernameEditText.error = null
            passwordEditText.error = null

            // Logika validasi
            var isValid = true
            if (username.isEmpty()) {
                usernameEditText.error = "Username tidak boleh kosong"
                isValid = false
            }

            if (password.isEmpty()) {
                passwordEditText.error = "Password tidak boleh kosong"
                isValid = false
            }

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