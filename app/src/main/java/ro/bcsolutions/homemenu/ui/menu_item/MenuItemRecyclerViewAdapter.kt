package ro.bcsolutions.homemenu.ui.menu_item

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.database.MenuItem
import ro.bcsolutions.homemenu.ui.edit_menu.EditMenuFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class MenuItemRecyclerViewAdapter: ListAdapter<MenuItem, MenuItemRecyclerViewAdapter.ViewHolder>(
    MenuItemListDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.menu_date)
        val lunch: TextView = itemView.findViewById(R.id.lunch_menu_item)
        val dinner: TextView = itemView.findViewById(R.id.dinner_menu_item)
        val layout: ConstraintLayout = itemView.findViewById(R.id.list_item_edit_home_menu_item_row)

        private val today = Calendar.getInstance()
        private val today_day = today.get(Calendar.DAY_OF_MONTH)
        private val today_month = today.get(Calendar.MONTH)
        private val today_year = today.get(Calendar.YEAR)

        fun bind(
            item: MenuItem
        ) {
            lunch.text = item.lunch
            dinner.text = item.dinner
            date.text = SimpleDateFormat("dd-MMMM").format(item.date.time)
            layout.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    EditMenuFragmentDirections.actionNavEditMenuToMenuItem(item.id)
                )
            )
            if (item.date.get(Calendar.DAY_OF_MONTH) == today_day && item.date.get(Calendar.MONTH) == today_month && item.date.get(
                    Calendar.YEAR
                ) == today_year
            ) {
                layout.setBackgroundColor(Color.parseColor("#A7FFEB"))
            } else {
                layout.setBackgroundColor(Color.WHITE)
                //date.setTextColor(Color.parseColor("#808080"))
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_edit_menu_item, parent, false)
                return ViewHolder(view)
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
}