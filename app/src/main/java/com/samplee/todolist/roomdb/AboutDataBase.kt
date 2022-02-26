package com.samplee.todolist.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.samplee.todolist.model.AboutData
import com.samplee.todolist.viewmodel.AboutViewmodel


/**************************************************
 * AboutDataBase() - This is database
 **************************************************/
@Database(entities = [AboutData::class], version = 1, exportSchema = false)

abstract class AboutDataBase : RoomDatabase() {

    abstract fun aboutDao() : AboutDaoOperations

    companion object {

        @Volatile
        private var instance: AboutDataBase? = null

        fun getAboutDBInstance(context: Context): AboutDataBase {
            if (instance != null)
                return instance!!
            synchronized(this){
                instance= Room.databaseBuilder(context,AboutDataBase::class.java,
                    "AboutDataBase.db")
                    .fallbackToDestructiveMigration()
                    .build()

                return instance!!
            }
        }

    }
}