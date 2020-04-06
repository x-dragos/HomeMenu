package ro.bcsolutions.homemenu.ui.menu_item

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.databinding.MenuItemFragmentBinding
import java.util.*
import ro.bcsolutions.homemenu.Utils


class MenuItemFragment : Fragment() {

    private val viewModel: MenuItemViewModel by viewModels()

    private lateinit var binding: MenuItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.menu_item_fragment, container, false);
        binding.menuDate.init(viewModel.menuDate.value!!.get(Calendar.YEAR), viewModel.menuDate.value!!.get(Calendar.MONTH), viewModel.menuDate.value!!.get(Calendar.DAY_OF_MONTH)) {
                _: DatePicker?, year: Int, month: Int, day: Int ->
            viewModel.setMenuDate(year,month,day);
        }
        binding.menuDate.formatDate("dmy")
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onDestroy() {
        activity?.let { Utils.hideKeyboard(it) };
        super.onDestroy()
    }

}
