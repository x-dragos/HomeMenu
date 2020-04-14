package ro.bcsolutions.homemenu.ui.menu_item

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

    private val today = Calendar.getInstance()
    private val today_day = today.get(Calendar.DAY_OF_MONTH)
    private val today_month = today.get(Calendar.MONTH)
    private val today_year = today.get(Calendar.YEAR)

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
        holder.dateDay.text = item.date.get(Calendar.DAY_OF_MONTH).toString()
        holder.dateMonth.text = SimpleDateFormat("MMM").format(item.date.time)
        holder.layout.setOnClickListener(Navigation.createNavigateOnClickListener(EditMenuFragmentDirections.actionNavEditMenuToMenuItem(item.id)))
        if (item.date.get(Calendar.DAY_OF_MONTH) == today_day && item.date.get(Calendar.MONTH) == today_month && item.date.get(Calendar.YEAR) == today_year) {
            holder.dateDay.setTextColor(Color.GREEN)
            holder.dateMonth.setTextColor(Color.GREEN)
        }
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dateDay: TextView = itemView.findViewById(R.id.menu_date_day)
        val dateMonth: TextView = itemView.findViewById(R.id.menu_date_month)
        val lunch: TextView = itemView.findViewById(R.id.lunch_menu_item)
        val dinner: TextView = itemView.findViewById(R.id.dinner_menu_item)
        val layout: ConstraintLayout = itemView.findViewById(R.id.list_item_edit_home_menu_item_row)
    }
}