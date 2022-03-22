package com.samplee.todolist.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.lifecycle.ViewModelProvider
import com.samplee.todolist.screens.Home
import com.samplee.todolist.screens.Main
import com.samplee.todolist.screens.theme.TodoListTheme
import com.samplee.todolist.viewmodel.AboutViewmodel
import kotlinx.coroutines.launch


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
//                MainScreenUI()
                Main(viewModel)
            }
        }
    }
}



