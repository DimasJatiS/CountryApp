package com.example.mypresensiapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mypresensiapp.databinding.FragmentLoginBinding
import kotlin.toString

class LoginFragment : Fragment() {

    private var listener: OnLoginSuccessListener? = null
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        if (context is OnLoginSuccessListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnLoginSuccessListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set tombol login tidak aktif di awal
        binding.btnLogin.isEnabled = false

        // Tambahkan listener ke EditText
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateInputs()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        binding.edtUsername.addTextChangedListener(textWatcher)
        binding.edtPw.addTextChangedListener(textWatcher)

        // Atur listener untuk tombol login
        binding.btnLogin.setOnClickListener {
            val username = binding.edtUsername.text.toString().trim()
            val password = binding.edtPw.text.toString().trim()

            if (username == "admin" && password == "admin") {
                Toast.makeText(activity, "Login Berhasil!", Toast.LENGTH_SHORT).show()
                listener?.onLoginSuccess() // Panggil listener!
            } else {
                Toast.makeText(activity, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }

        // Atur listener untuk tombol register jika diperlukan
        binding.btnRegister.setOnClickListener {
            // Tambahkan logika untuk pindah ke tab register
        }
    }

    private fun validateInputs() {
        val username = binding.edtUsername.text.toString().trim()
        val password = binding.edtPw.text.toString().trim()

        // Tombol login akan aktif jika kedua field tidak kosong
        binding.btnLogin.isEnabled = username.isNotEmpty() && password.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
interface OnLoginSuccessListener {
    fun onLoginSuccess()
}

