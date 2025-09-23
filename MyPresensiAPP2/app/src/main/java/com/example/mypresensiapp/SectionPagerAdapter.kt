import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    // Jumlah total tab/fragment
    override fun getItemCount(): Int {
        return 2
    }

    // Menentukan fragment mana yang akan ditampilkan berdasarkan posisi tab
    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = RegisterFragment()
            1 -> fragment = LoginFragment()
        }
        return fragment as Fragment
    }
}