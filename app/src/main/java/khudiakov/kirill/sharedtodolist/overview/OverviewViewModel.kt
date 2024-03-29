package khudiakov.kirill.sharedtodolist.overview

import android.util.Log
import androidx.lifecycle.ViewModel
import khudiakov.kirill.sharedtodolist.database.TodoList
import khudiakov.kirill.sharedtodolist.database.TodoListDao
import kotlinx.coroutines.*

class OverviewViewModel(private val todoListDao: TodoListDao)
    : ViewModel() {

    val lists = todoListDao.getAllLists()

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    fun addList(name: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val list = TodoList(name)
            todoListDao.insert(list)
        }
    }
}
