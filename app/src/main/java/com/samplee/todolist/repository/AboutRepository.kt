package com.samplee.todolist.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samplee.todolist.model.AboutData
import com.samplee.todolist.roomdb.AboutDaoOperations
import com.samplee.todolist.roomdb.AboutDataBase


/***************************************************************************************
 * AboutRepository() - This for communicate the table and manipulate the db operation
 *************************************************************************************/
class AboutRepository(con: Context) {

    var aboutdb: AboutDataBase? = null
    var aboutdao: AboutDaoOperations? =null;

    private var _abouts: LiveData<List<AboutData>> = MutableLiveData()

    val tasks: LiveData<List<AboutData>> get() = _abouts

    fun initializeAboutDB(context: Context) {
        aboutdb = AboutDataBase.getAboutDBInstance(context)
        getDao()
    }

    fun getDao(){
        aboutdao = aboutdb?.aboutDao()!!
    }

    fun fetchAll() = aboutdao?.fetchAll()

    fun fetchById(id: Int) = aboutdao?.fetchById(id)

    suspend fun insert( about: AboutData) = aboutdao?.insert(about)

    suspend fun update( about: AboutData) = aboutdao?.update(about)
}