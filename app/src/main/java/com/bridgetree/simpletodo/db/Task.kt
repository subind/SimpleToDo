package com.bridgetree.simpletodo.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**Note:- If you don't to provide id, make the column optional/nullable, i.e '?'
 * https://stackoverflow.com/a/55097140/9793057
 * */

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id : Int?,
    var taskName : String
) {
}