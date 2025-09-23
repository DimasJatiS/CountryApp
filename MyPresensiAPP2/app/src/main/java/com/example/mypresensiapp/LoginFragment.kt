import android.content.Intent // Import Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.example.mypresensiapp.R// Import Button

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogin: AppCompatButton = view.findViewById(R.id.btnLogin) // Ganti ID jika berbeda

        btnLogin.setOnClickListener {
            // Pindah ke HomeActivity
            val intent = Intent(activity, HomeActivity::class.java)
            startActivity(intent)
            // Tutup activity saat ini (MainActivity)
            activity?.finish()
        }
    }
}