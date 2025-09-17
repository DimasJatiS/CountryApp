// File: TimePickerFragment.kt
package com.example.presensi // Sesuaikan dengan paket Anda

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.Calendar

class TimePickerFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = requireActivity()
        val listener = activity as TimePickerDialog.OnTimeSetListener
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Buat TimePickerDialog dengan MENYERTAKAN TEMA KUSTOM
        val dialog = TimePickerDialog(
            context,
            R.style.CustomPickerDialogTheme, // <-- TERAPKAN TEMA DI SINI
            listener,
            hour,
            minute,
            false // Gunakan format 12 jam (false) atau 24 jam (true)
        )

        return dialog
    }
}