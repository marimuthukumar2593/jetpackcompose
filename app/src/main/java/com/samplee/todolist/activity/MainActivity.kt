package com.samplee.todolist.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.samplee.todolist.screens.Home
import com.samplee.todolist.screens.Main
import com.samplee.todolist.screens.theme.TodoListTheme
import com.samplee.todolist.viewmodel.AboutViewmodel


/****************************************************************
 * MainActivity() - this class for launch the application view
 ****************************************************************/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(AboutViewmodel::class.java)
        viewModel.initRepository(this)

        setContent {

            TodoListTheme {
                Main(viewModel)
            }
        }
    }
}

