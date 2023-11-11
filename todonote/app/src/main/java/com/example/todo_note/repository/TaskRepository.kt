package com.example.todo_note.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.todo_note.dao.TaskDao
import com.example.todo_note.database.TaskDatabase
import com.example.todo_note.models.Task
import com.example.todo_note.utils.Resource
import com.example.todo_note.utils.Resource.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskRepository(application: Application) {

    private val taskDao: TaskDao = TaskDatabase.getInstance(application).taskDao

    fun insertTask(task: Task) = MutableLiveData<Resource<Long>>().apply {
        postValue(Loading())

        try {
            CoroutineScope(Dispatchers.IO).launch {
                val result = taskDao.insertTask(task)
                postValue(Success(result))
            }
        } catch (e: Exception) {
            postValue(Error(e.message.toString()))
        }
    }
}