package com.example.presensi

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.presensi.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup spinner
        setupSpinner()
        setupCalendarView()
        setupTimePicker()

        // Setup button
        setupSubmitButton()
        setupExitButton()
    }

    private fun setupSpinner() {
        val keteranganArray = resources.getStringArray(R.array.keterangan)
        val adapterKeterangan = ArrayAdapter(this, android.R.layout.simple_spinner_item, keteranganArray)
        adapterKeterangan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerKeterangan.adapter = adapterKeterangan

        // Setup Spinner alasan "Keterangan"
        binding.spinnerKeterangan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem == "Sakit" || selectedItem == "Terlambat" || selectedItem == "Izin") {
                    binding.cardAlasan.visibility = View.VISIBLE
                } else {
                    binding.cardAlasan.visibility = View.GONE
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                binding.cardAlasan.visibility = View.GONE
            }
        }

        // month Spinner
        val calendar = Calendar.getInstance()
        val monthAdapter = ArrayAdapter.createFromResource(this, R.array.months, android.R.layout.simple_spinner_item)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMonth.adapter = monthAdapter
        binding.spinnerMonth.setSelection(calendar.get(Calendar.MONTH))

        // year Spinner
        val currentYear = calendar.get(Calendar.YEAR)
        val years = ArrayList<String>()
        for (i in (currentYear - 100)..(currentYear + 100)) {
            years.add(i.toString())
        }
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerYear.adapter = yearAdapter
        binding.spinnerYear.setSelection(years.indexOf(currentYear.toString()))

        val selectionListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateCalendarDate()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.spinnerMonth.onItemSelectedListener = selectionListener
        binding.spinnerYear.onItemSelectedListener = selectionListener
    }

    // Setup Calendar (memilih dan pesan toast)
    private fun setupCalendarView() {
        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/$year"
                Toast.makeText(this, "Tanggal Dipilih: $selectedDate", Toast.LENGTH_SHORT).show()
            binding.spinnerMonth.setSelection(month)
            val yearStr = year.toString()
            val adapter = binding.spinnerYear.adapter as ArrayAdapter<String>
            val position = adapter.getPosition(yearStr)
            if (position >= 0) {
                binding.spinnerYear.setSelection(position)
            }
        }
    }

    // setup update calendar
    private fun updateCalendarDate() {
        val selectedMonth = binding.spinnerMonth.selectedItemPosition
        val selectedYear = binding.spinnerYear.selectedItem.toString().toInt()
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, selectedYear)
        calendar.set(Calendar.MONTH, selectedMonth)
        binding.calendarView.date = calendar.timeInMillis
    }

    // Setup Picker waktu dan pesan Toast
    private fun setupTimePicker() {
        binding.timePickerSpinner.setIs24HourView(false)
        binding.timePickerSpinner.setOnTimeChangedListener { view, hourOfDay, minute ->
             val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
             Toast.makeText(this, "Waktu berubah menjadi: $selectedTime", Toast.LENGTH_SHORT).show()
        }
    }

    // setup button submit
    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            // 1. Ambil data tanggal dari CalendarView
            val calendar = Calendar.getInstance().apply {
                timeInMillis = binding.calendarView.date
            }
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val tanggal = dateFormat.format(calendar.time)

            // 2. Ambil data waktu dari TimePicker
            val jam = binding.timePickerSpinner.hour
            val menit = binding.timePickerSpinner.minute
            val waktu = String.format("%02d:%02d", jam, menit)

            // 3. Ambil data status dari Spinner
            val status = binding.spinnerKeterangan.selectedItem.toString()

            // 4. Ambil data alasan (jika ada)
            var alasan = "Tidak ada"
            if (binding.cardAlasan.visibility == View.VISIBLE) {
                alasan = binding.etAlasan.text.toString()
                // Validasi: jika alasan wajib diisi
                if (alasan.isBlank()) {
                    Toast.makeText(this, "Alasan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener // Hentikan proses jika alasan kosong
                }
            }

            // 5. Gabungkan semua data menjadi satu pesan ringkasan
            val ringkasan = """
                Presensi Berhasil!
                Tanggal: $tanggal
                Waktu: $waktu
                Status: $status
                Alasan: $alasan
            """.trimIndent()

            // 6. Tampilkan ringkasan di Toast
            Toast.makeText(this, ringkasan, Toast.LENGTH_LONG).show()
        }
    }

    // Setup button exit aplikasi
    private fun setupExitButton() {
        binding.btnExit.setOnClickListener {
            val dialog = DialogExit()
            dialog.show(supportFragmentManager, "dialogExit")
        }
    }
}