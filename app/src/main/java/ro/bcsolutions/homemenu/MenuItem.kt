package ro.bcsolutions.homemenu

import androidx.room.*

@Entity(tableName = "menu_items_table")
data class MenuItem(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    @ColumnInfo()
    var date: Long,
    @ColumnInfo()
    var pranz: String,
    @ColumnInfo()
    var cina: String
)

@Dao
interface MenuItemDAO {
    @Insert
    fun insert(menuItem: MenuItem)

    @Update
    fun update(menuItem: MenuItem)

    @Delete
    fun delete(menuItem: MenuItem)

    @Query("SELECT * FROM `menu_items_table` ORDER by `date`")
    fun get_all(): Array<MenuItem>

    @Query("SELECT * FROM `menu_items_table` WHERE `id`= :key")
    fun get(key: Long): MenuItem
}