package ro.bcsolutions.homemenu.ui.edit_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.add_menu_item_fragment.view.*
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.databinding.AddMenuItemFragmentBinding
import java.util.*


class AddMenuItemFragment : Fragment() {

    /*companion object {
        fun newInstance() =
            AddMenuItemFragment()
    }*/

    private val viewModel: AddMenuItemViewModel by viewModels()

    private lateinit var binding: AddMenuItemFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_menu_item_fragment, container, false);
        binding.menuDate.updateDate(viewModel.menuDate.get(Calendar.YEAR), viewModel.menuDate.get(Calendar.MONTH), viewModel.menuDate.get(Calendar.DAY_OF_MONTH)+3)
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        val test = 1
    }

}
