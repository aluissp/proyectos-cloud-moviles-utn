package com.example.todo_note.dao

import androidx.room.*
import com.example.todo_note.models.Task

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task): Long
}