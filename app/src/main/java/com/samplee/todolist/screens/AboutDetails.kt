package com.samplee.todolist.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.samplee.todolist.R
import com.samplee.todolist.model.AboutData
import com.samplee.todolist.theme.graySurface
import com.samplee.todolist.viewmodel.AboutViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/****************************************************************
 * AboutDetails() - Create the abouts details view
 ****************************************************************/

@Composable
fun AboutDetails(
    viewModel: AboutViewmodel,
    pressOnBack: () -> Unit
) {
    var desc by remember { mutableStateOf("") }
    val about: AboutData? by viewModel.about.observeAsState()

    about?.let {
        desc = it.desc
    }
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = { TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.about_title),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(200.dp)
                )
            },
            navigationIcon = {
                IconButton(onClick = { pressOnBack() }) {
                    Icon(Icons.Filled.ArrowBack, "Back", tint = Color.Black)
                }
            },
            backgroundColor = graySurface,
        )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {

                if (about != null) {
                    viewModel.update(AboutData(about!!.id, desc))
                    pressOnBack()
                } else {

                    if(!desc.isBlank()&&!desc.isEmpty()) {
                        viewModel.insert(AboutData(0, desc))
                        pressOnBack()
                    }
                    else{
                        showSnackBar(snackbarCoroutineScope,scaffoldState)
                    }
                }
            }) {
                Icon(Icons.Filled.Done, "")
            }
        },
        scaffoldState  = scaffoldState
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(4.dp),
            verticalArrangement = Arrangement.Top
        ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            graySurface, shape = RoundedCornerShape(8.dp)
                        )
                ){
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(graySurface)
                        .height(200.dp),
                    value = desc,
                    onValueChange = { desc = it },
                    placeholder = { Text(text = "Type here..") },
                    label = { Text("About") })

            }

        }
    }
}

fun showSnackBar(snackbarCoroutineScope: CoroutineScope, scaffoldState: ScaffoldState,) {
    snackbarCoroutineScope.launch {
        scaffoldState.snackbarHostState.showSnackbar("About description should not be empty",actionLabel = "OK")
    }
}
/****************************************************************
 * showMessage() - show the toast message
 ****************************************************************/

fun showMessage(context: Context, message:String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
