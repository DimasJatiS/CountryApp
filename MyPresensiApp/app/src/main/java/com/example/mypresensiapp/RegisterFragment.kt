package com.example.mypresensiapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mypresensiapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set tombol register tidak aktif di awal
        binding.btnRegister.isEnabled = false

        // Tambahkan listener ke setiap EditText dan CheckBox
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateInputs()
            }
            override fun afterTextChanged(s: Editable?) {}
        }

        binding.edtUsername.addTextChangedListener(textWatcher)
        binding.edtEmail.addTextChangedListener(textWatcher)
        binding.edtPhone.addTextChangedListener(textWatcher)
        binding.edtPw.addTextChangedListener(textWatcher)
        binding.checkBox.setOnCheckedChangeListener { _, _ -> validateInputs() }

        // Atur listener untuk tombol register
        binding.btnRegister.setOnClickListener {
            // Logika ketika tombol register di-klik
            val username = binding.edtUsername.text.toString()
            Toast.makeText(activity, "Registrasi berhasil untuk user: $username", Toast.LENGTH_SHORT).show()
            // Di sini Anda bisa menambahkan logika lain, seperti menyimpan data ke database
        }

        // Atur listener untuk tombol login jika diperlukan
        binding.btnLogin.setOnClickListener {
            // Tambahkan logika untuk pindah ke tab login, misalnya:
            // (activity as? YourMainActivity)?.viewPager?.currentItem = 0
        }
    }

    private fun validateInputs() {
        val username = binding.edtUsername.text.toString().trim()
        val email = binding.edtEmail.text.toString().trim()
        val phone = binding.edtPhone.text.toString().trim()
        val password = binding.edtPw.text.toString().trim()
        val isChecked = binding.checkBox.isChecked

        // Tombol register akan aktif jika semua field terisi DAN checkbox tercentang
        binding.btnRegister.isEnabled = username.isNotEmpty() &&
                email.isNotEmpty() &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                phone.isNotEmpty() &&
                password.length >= 8 && // Contoh: password minimal 8 karakter
                isChecked
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}