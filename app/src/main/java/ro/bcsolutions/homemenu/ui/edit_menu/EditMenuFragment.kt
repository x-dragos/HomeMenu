package ro.bcsolutions.homemenu.ui.edit_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.database.HomeMenuDatabase
import ro.bcsolutions.homemenu.databinding.EditMenuFragmentBinding
import ro.bcsolutions.homemenu.ui.menu_item.MenuItemRecyclerViewAdapter

class EditMenuFragment : Fragment() {

    private lateinit var editMenuViewModel: EditMenuViewModel

    private lateinit var binding: EditMenuFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val homeMenuItemDao = HomeMenuDatabase.getInstance(application).homeMenuItemDao

        editMenuViewModel = ViewModelProvider(this,EditMenuViewModelFactory(homeMenuItemDao)).get(EditMenuViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,R.layout.edit_menu_fragment, container, false)
        binding.editMenuViewModel = editMenuViewModel
        binding.fabAddMenuItem.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_nav_edit_menu_to_menu_item))

        val adapter = MenuItemRecyclerViewAdapter()
        binding.editMenuItemsList.adapter = adapter
        editMenuViewModel.menuItems.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}