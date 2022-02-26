package com.samplee.todolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/****************************************************************
 * AboutData() - This is data class for store the data as table
 ****************************************************************/
@Entity (tableName ="About")
data class AboutData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "about_desc")
    var desc: String
)
