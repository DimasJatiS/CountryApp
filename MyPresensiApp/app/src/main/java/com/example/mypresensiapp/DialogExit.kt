import android.app.AlertDialog
import android.content.Context
import com.example.mypresensiapp.databinding.DialogExitBinding

class DialogExit(private val context: Context) {

    private lateinit var binding: DialogExitBinding

    fun show() {
        binding = DialogExitBinding.inflate((context as android.app.Activity).layoutInflater)

        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        val dialog = builder.create()

        binding.btnYes.setOnClickListener {
            // Logika untuk tombol "Yes"
            dialog.dismiss()
            (context as android.app.Activity).finish()
        }

        binding.btnNo.setOnClickListener {
            // Logika untuk tombol "No"
            dialog.dismiss()
        }

        dialog.show()
    }
}