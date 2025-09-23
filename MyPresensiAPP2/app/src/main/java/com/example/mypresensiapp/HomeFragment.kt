import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.example.mypresensiapp.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_home)
        setSupportActionBar(toolbar)
    }

    // Method untuk menampilkan menu di Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    // Method untuk menangani klik pada item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Cek item mana yang diklik
        if (item.itemId == R.id.action_logout) {
            showLogoutDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    // Method untuk menampilkan dialog konfirmasi logout
    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Keluar")
        builder.setMessage("Apakah Anda yakin ingin keluar?")
        builder.setPositiveButton("Ya") { _, _ ->
            // Aksi jika pengguna menekan "Ya"
            // Kembali ke MainActivity (halaman login/register)
            val intent = Intent(this, MainActivity::class.java)
            // Hapus semua activity sebelumnya dari back stack
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish() // Tutup HomeActivity
        }
        builder.setNegativeButton("Tidak") { dialog, _ ->
            // Aksi jika pengguna menekan "Tidak"
            dialog.dismiss() // Tutup dialog
        }
        val dialog = builder.create()
        dialog.show()
    }
}