package ro.bcsolutions.homemenu.ui.menu_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.databinding.MenuItemFragmentBinding
import java.util.*
import ro.bcsolutions.homemenu.Utils
import ro.bcsolutions.homemenu.database.HomeMenuDatabase


class MenuItemFragment : Fragment() {

    private lateinit var menuItemViewModel: MenuItemViewModel

    private lateinit var binding: MenuItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val homeMenuItemDao = HomeMenuDatabase.getInstance(application).homeMenuItemDao
        menuItemViewModel = ViewModelProvider(this, MenuItemViewModelFactory(homeMenuItemDao)).get(MenuItemViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.menu_item_fragment, container, false)

        binding.menuDate.formatDate("dmy")

        binding.menuItemViewModel = menuItemViewModel

        binding.lifecycleOwner = this

        menuItemViewModel.menuDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.menuDate.init(menuItemViewModel.menuDate.value!!.get(Calendar.YEAR), menuItemViewModel.menuDate.value!!.get(Calendar.MONTH), menuItemViewModel.menuDate.value!!.get(Calendar.DAY_OF_MONTH)) {
                    _: DatePicker?, year: Int, month: Int, day: Int ->
                menuItemViewModel.setMenuDate(year,month,day)
            }
        })

        binding.buttonSaveMenuItem.setOnClickListener {
            menuItemViewModel.saveMenuItem()
            findNavController().navigate(R.id.action_nav_menu_item_to_nav_edit_menu)
        }

        return binding.root
    }

    override fun onDestroy() {
        activity?.let { Utils.hideKeyboard(it) }
        super.onDestroy()
    }

}
