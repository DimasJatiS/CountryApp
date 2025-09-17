// File: TimePickerFragment.kt
package com.example.presensi

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

        // Buat TimePickerDialog
        val dialog = TimePickerDialog(
            context,
            R.style.CustomPickerDialogTheme,
            listener,
            hour,
            minute,
            false
        )

        return dialog
    }
}