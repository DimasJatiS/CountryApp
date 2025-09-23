// File: DialogExit.kt
package com.example.tugasmenggila

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogExit : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater

        // Setup layout kustom
        val dialogView = inflater.inflate(R.layout.dialog_exit, null)

        // Setup Tombol untuk Keluar aplikasi dan Logout
        val btnYes: Button = dialogView.findViewById(R.id.btn_yes)
        val btnNo: Button = dialogView.findViewById(R.id.btn_no)

        btnYes.setOnClickListener {
            // Tutup aplikasi
            requireActivity().finish()
        }
        btnNo.setOnClickListener {
            // Tutup dialognya
            dismiss()
        }

        // Pemgaturan layout kustom sebagai konten dialog
        builder.setView(dialogView)
        return builder.create()
    }
}