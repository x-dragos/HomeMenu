package ro.bcsolutions.homemenu.ui.menu_item

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.bcsolutions.homemenu.database.MenuItem
import ro.bcsolutions.homemenu.databinding.ListItemEditMenuItemBinding
import ro.bcsolutions.homemenu.databinding.ListItemHomeMenuItemBinding
import java.util.*

class MenuItemRecyclerViewAdapter(private val type: HomeMenuListType, private val clickListener: MenuItemClickListener): ListAdapter<MenuItem, MenuItemRecyclerViewAdapter.ViewHolder>(
    MenuItemListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, type)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position))
    }

    class ViewHolder private constructor(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){

        private val today = Calendar.getInstance()
        private val todayDay = today.get(Calendar.DAY_OF_MONTH)
        private val todayMonth = today.get(Calendar.MONTH)
        private val todayYear = today.get(Calendar.YEAR)

        fun bind(
            clickListener: MenuItemClickListener,
            item: MenuItem
        ) {
            when (binding) {
                is ListItemEditMenuItemBinding -> {
                    binding.menuItem = item
                    binding.executePendingBindings()

                    binding.menuItemClickListener = clickListener

                    if (item.date.get(Calendar.DAY_OF_MONTH) == todayDay && item.date.get(Calendar.MONTH) == todayMonth && item.date.get(
                            Calendar.YEAR
                        ) == todayYear
                    ) {
                        binding.listItemEditMenuItemRow.setBackgroundColor(Color.parseColor("#A7FFEB"))
                    } else {
                        binding.listItemEditMenuItemRow.setBackgroundColor(Color.WHITE)
                    }
                }
                is ListItemHomeMenuItemBinding -> {
                    binding.menuItem = item
                    binding.executePendingBindings()

                    binding.menuItemClickListener = clickListener

                    if (item.date.get(Calendar.DAY_OF_MONTH) == todayDay && item.date.get(Calendar.MONTH) == todayMonth && item.date.get(
                            Calendar.YEAR
                        ) == todayYear
                    ) {
                        binding.listItemHomeMenuItemRow.setBackgroundColor(Color.parseColor("#A7FFEB"))
                    } else {
                        binding.listItemHomeMenuItemRow.setBackgroundColor(Color.WHITE)
                    }
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, type: HomeMenuListType): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = when (type) {
                    HomeMenuListType.HOME -> ListItemHomeMenuItemBinding.inflate(layoutInflater, parent, false)
                    HomeMenuListType.EDIT -> ListItemEditMenuItemBinding.inflate(layoutInflater, parent, false)
                }
                return ViewHolder(binding)
            }
        }
    }

    class MenuItemListDiffCallback : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
            return oldItem == newItem
        }
    }

    class MenuItemClickListener(private val callback: (menuItemId: Long) -> Unit) {
        fun onClick(menuItem: MenuItem) = callback(menuItem.id)
    }

    enum class HomeMenuListType {
        EDIT, HOME
    }
}