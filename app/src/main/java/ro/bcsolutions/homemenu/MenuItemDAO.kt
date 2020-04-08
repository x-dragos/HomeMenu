package ro.bcsolutions.homemenu

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MenuItemDAO {
    @Insert
    fun insert(menuItem: MenuItem)

    @Update
    fun update(menuItem: MenuItem)

    @Delete
    fun delete(menuItem: MenuItem)

    @Query("SELECT * FROM `menu_items_table` ORDER by date(`date`)")
    fun get_all(): LiveData<List<MenuItem>>

    @Query("SELECT * FROM `menu_items_table` WHERE `id`= :key")
    fun get(key: Long): MenuItem
}