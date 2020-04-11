package ro.bcsolutions.homemenu.database

import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface HomeMenuItemDao {
    @Insert
    fun insert (menuItem: MenuItem) : Long

    @Update
    fun update(menuItem: MenuItem)

    @Delete
    fun delete(menuItem: MenuItem)

    @Query("SELECT * FROM `menu_items_table` ORDER by date(`date`)")
    fun getAll(): LiveData<List<MenuItem>?>

    @Query("SELECT * FROM `menu_items_table` WHERE `id`= :key")
    fun get(key: Long): MenuItem?

    @Query("SELECT max(date) FROM `menu_items_table`")
    fun getLastMenuDate(): Calendar?
}