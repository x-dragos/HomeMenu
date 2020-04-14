package ro.bcsolutions.homemenu.ui.menu_item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import ro.bcsolutions.homemenu.R
import ro.bcsolutions.homemenu.database.MenuItem
import ro.bcsolutions.homemenu.ui.edit_menu.EditMenuFragmentDirections
import java.text.SimpleDateFormat
import java.util.*

class MenuItemRecyclerViewAdapter: RecyclerView.Adapter<MenuItemRecyclerViewAdapter.ViewHolder>() {

    var data = listOf<MenuItem>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_edit_home_menu_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.lunch.text = item.lunch
        holder.dinner.text = item.dinner
        val formatter = SimpleDateFormat("dd-MMM")
        holder.date.text = formatter.format(item.date.time)
        holder.button.setOnClickListener(Navigation.createNavigateOnClickListener(EditMenuFragmentDirections.actionNavEditMenuToMenuItem(item.id)))
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val date: TextView = itemView.findViewById(R.id.menu_date)
        val lunch: TextView = itemView.findViewById(R.id.lunch_menu_item)
        val dinner: TextView = itemView.findViewById(R.id.dinner_menu_item)
        val button: Button = itemView.findViewById(R.id.button_edit_menu_item)
    }
}