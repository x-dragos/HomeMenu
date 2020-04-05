package ro.bcsolutions.homemenu.ui.home_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ro.bcsolutions.homemenu.R

class HomeMenuFragment : Fragment() {

    private val homeMenuViewModel: HomeMenuViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home_menu_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeMenuViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
