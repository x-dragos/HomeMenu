package ro.bcsolutions.homemenu.ui.edit_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.databinding.EditMenuFragmentBinding

class EditMenuFragment : Fragment() {

    private val viewModel: EditMenuViewModel by viewModels()

    private lateinit var binding: EditMenuFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: EditMenuFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.edit_menu_fragment, container, false);
        //val root = inflater.inflate(R.layout.edit_menu_fragment, container, false)
        //val textView: TextView = root.findViewById(R.id.text_edit_menu)
        /*viewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textEditMenu.text = it
        })*/

        binding.viewmodel = viewModel
        binding.fabAddMenuItem.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_edit_menu_to_menu_item));

        return binding.root
    }
}