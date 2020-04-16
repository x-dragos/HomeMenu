package ro.bcsolutions.homemenu.ui.home_menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.database.HomeMenuDatabase
import ro.bcsolutions.homemenu.databinding.HomeMenuFragmentBinding
import ro.bcsolutions.homemenu.ui.menu_item.MenuItemRecyclerViewAdapter

class HomeMenuFragment : Fragment() {

    private lateinit var homeMenuViewModel: HomeMenuViewModel

    private lateinit var binding: HomeMenuFragmentBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(this.activity).application
        val homeMenuItemDao = HomeMenuDatabase.getInstance(application).homeMenuItemDao

        homeMenuViewModel = ViewModelProvider(this, HomeMenuViewModelFactory(homeMenuItemDao)).get(HomeMenuViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater,R.layout.home_menu_fragment,container, false)
        binding.homeMenuViewModel = homeMenuViewModel

        val adapter = MenuItemRecyclerViewAdapter(MenuItemRecyclerViewAdapter.HomeMenuListType.HOME,MenuItemRecyclerViewAdapter.MenuItemClickListener {  })
        binding.homeMenuItemsList.adapter = adapter

        homeMenuViewModel.menuItems.observe(viewLifecycleOwner, Observer { menuItemList ->
            adapter.submitList(menuItemList)
        })

        return binding.root
    }
}
