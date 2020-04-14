package ro.bcsolutions.homemenu.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [MenuItem::class], version = 2, exportSchema = true)
@TypeConverters(Converters::class)
abstract class HomeMenuDatabase : RoomDatabase() {

    abstract val homeMenuItemDao: HomeMenuItemDao

    companion object {
        @Volatile
        private var INSTANCE: HomeMenuDatabase? = null

        fun getInstance(context: Context): HomeMenuDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        HomeMenuDatabase::class.java, "home_menu_database").fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}