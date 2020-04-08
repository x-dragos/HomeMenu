package ro.bcsolutions.homemenu

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MenuItem::class], version = 1, exportSchema = true)
abstract class HomeMenuDatabase : RoomDatabase() {

    abstract val menuItemDatabaseDAO: MenuItemDAO

    companion object {
        @Volatile
        private var INSTANCE: HomeMenuDatabase? = null

        fun getInstance(context: Context): HomeMenuDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,HomeMenuDatabase::class.java, "home_menu_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}