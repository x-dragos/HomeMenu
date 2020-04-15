package ro.bcsolutions.homemenu.ui.menu_item

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.Utils
import ro.bcsolutions.homemenu.database.HomeMenuDatabase
import ro.bcsolutions.homemenu.databinding.MenuItemFragmentBinding
import java.util.*


class MenuItemFragment : Fragment() {

    private lateinit var menuItemViewModel: MenuItemViewModel

    private lateinit var binding: MenuItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(this.activity).application
        val homeMenuItemDao = HomeMenuDatabase.getInstance(application).homeMenuItemDao

        val args = arguments?.let { MenuItemFragmentArgs.fromBundle(it) }

        menuItemViewModel = ViewModelProvider(
            this,
            MenuItemViewModelFactory(homeMenuItemDao, args?.menuItemId ?: 0L)
        ).get(MenuItemViewModel::class.java)



        binding = DataBindingUtil.inflate(inflater, R.layout.menu_item_fragment, container, false)

        if (Build.VERSION.SDK_INT < 22) {
            binding.menuDate.formatDate("dmy")
        }


        binding.menuItemViewModel = menuItemViewModel

        binding.lifecycleOwner = this

        menuItemViewModel.menuDate.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            binding.menuDate.init(
                menuItemViewModel.menuDate.value!!.get(Calendar.YEAR),
                menuItemViewModel.menuDate.value!!.get(Calendar.MONTH),
                menuItemViewModel.menuDate.value!!.get(Calendar.DAY_OF_MONTH)
            ) { _: DatePicker?, year: Int, month: Int, day: Int ->
                menuItemViewModel.setMenuDate(year, month, day)
            }
        })

        binding.buttonSaveMenuItem.setOnClickListener {
            Utils.hideKeyboard(this.requireActivity())
            menuItemViewModel.saveMenuItem()
            findNavController().navigate(R.id.action_nav_menu_item_to_nav_edit_menu)
        }

        val editTexts = listOf( binding.menuCina.editText, binding.menuPranz.editText)
        editTexts.forEach {
            it?.setOnTouchListener { v, event ->
                if (v.hasFocus()) {
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    when (event.action and MotionEvent.ACTION_MASK) {
                        MotionEvent.ACTION_SCROLL -> {
                            v.parent.requestDisallowInterceptTouchEvent(false)
                        }
                    }
                }
                false
            }
        }

        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { Utils.hideKeyboard(it) }
    }

}
