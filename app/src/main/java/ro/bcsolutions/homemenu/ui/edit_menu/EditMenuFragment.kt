package ro.bcsolutions.homemenu.ui.edit_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

        val adapter = MenuItemRecyclerViewAdapter(MenuItemRecyclerViewAdapter.HomeMenuListType.EDIT,MenuItemRecyclerViewAdapter.MenuItemClickListener { menuItemId ->
            editMenuViewModel.onMenuItemClicked(menuItemId)
        })

        binding.editMenuItemsList.adapter = adapter

        editMenuViewModel.menuItems.observe(viewLifecycleOwner, Observer { menuItemList ->
            adapter.submitList(menuItemList)
        })

        editMenuViewModel.navigateToMenuItemFragment.observe(viewLifecycleOwner, Observer {menuItemId ->
            menuItemId?.let {
                findNavController().navigate(
                    EditMenuFragmentDirections.actionNavEditMenuToMenuItem(
                        menuItemId
                    )
                )
                editMenuViewModel.onMenuItemFragmentNavigated()
            }
        })

        return binding.root
    }
}