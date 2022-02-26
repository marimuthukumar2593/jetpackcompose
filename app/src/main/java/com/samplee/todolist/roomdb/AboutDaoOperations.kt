package com.samplee.todolist.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.samplee.todolist.model.AboutData

/*****************************************************************************
 * AboutDaoOperations() - This is database operation insert,update,delete,read
 ******************************************************************************/
@Dao
interface AboutDaoOperations {

    @Query("select * from About")
    fun fetchAll(): LiveData<List<AboutData>>

    @Query("select * from About where id =:id")
    fun fetchById(id: Int): LiveData<AboutData>

    @Insert
    suspend fun insert(about: AboutData)

    @Update
    suspend fun update( about: AboutData)
}