package ro.bcsolutions.homemenu

/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ro.bcsolutions.homemenu.database.HomeMenuDatabase
import ro.bcsolutions.homemenu.database.MenuItem
import ro.bcsolutions.homemenu.database.HomeMenuItemDao
import java.io.IOException
import java.util.*

/**
 * This is not meant to be a full set of tests. For simplicity, most of your samples do not
 * include tests. However, when building the Room, it is helpful to make sure it works before
 * adding the UI.
 */

@RunWith(AndroidJUnit4::class)
class HomeMenuDatabaseTest {

    private lateinit var homeMenuItemDao: HomeMenuItemDao
    private lateinit var db: HomeMenuDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context,HomeMenuDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        homeMenuItemDao = db.homeMenuItemDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetNight() {
        val menuItem = MenuItem(
            date = Calendar.getInstance(),
            lunch = "Supa",
            dinner = "Lava cake + Inghetata"
        )
       val insered_id = homeMenuItemDao.insert(menuItem)
        val dbMenuItem = homeMenuItemDao.get(insered_id)
        assertEquals(dbMenuItem?.id, insered_id)
        assertEquals(dbMenuItem?.lunch, "Supa")
        assertEquals(dbMenuItem?.dinner, "Lava cake + Inghetata")
    }
}
