package ro.bcsolutions.homemenu.database

import androidx.room.*
import java.util.*

@Entity(tableName = "menu_items_table")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo()
    var date: Calendar,
    @ColumnInfo()
    var lunch: String,
    @ColumnInfo()
    var dinner: String
)