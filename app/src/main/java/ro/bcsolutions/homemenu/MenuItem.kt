package ro.bcsolutions.homemenu

import androidx.room.*
import java.util.*

@Entity(tableName = "menu_items_table")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo()
    var date: Calendar? = null,
    @ColumnInfo()
    var pranz: String,
    @ColumnInfo()
    var cina: String
)