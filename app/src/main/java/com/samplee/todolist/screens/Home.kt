package com.samplee.todolist.screens

import androidx.compose.animation.Animatable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.samplee.todolist.R
import com.samplee.todolist.model.AboutData
import com.samplee.todolist.theme.graySurface
import com.samplee.todolist.utils.Routes
import com.samplee.todolist.viewmodel.AboutViewmodel


/****************************************************************
 * Home() - Show the abouts lists as home view
 ****************************************************************/

@Composable
fun Home(viewmodel: AboutViewmodel, navController: NavHostController,
         selectTask: (Int) -> Unit) {

    val abouts: List<AboutData> by viewmodel.abouts.observeAsState(listOf())
    viewmodel.fetchAll()

    Scaffold(
        topBar = { TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.home_title),
                textAlign = TextAlign.Center,
                modifier = Modifier.width(200.dp)
            ) },backgroundColor = graySurface)
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.AboutScreen.routeWithoutArgument)
            }) {
                Icon(Icons.Filled.Add, "")
            }
        }
    )
    {
        AboutList(abouts, selectTask)
    }

}

/****************************************************************
 * AboutList() - create the about lists as recyclerview
 ****************************************************************/
@Composable
fun AboutList(abouts: List<AboutData>, selectTask: (Int) -> Unit) {
    LazyColumn {
        items(abouts) { about ->
            AboutCardView(about, selectTask)
        }
    }
}

/****************************************************************
 * AboutCardView() - Create the abouts item as card view
 ****************************************************************/

@Composable
fun AboutCardView(about:AboutData,selectTask: (Int) -> Unit){
    val color = remember { Animatable(Color.LightGray) }

    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable { selectTask(about.id) },
        backgroundColor = color.value,
        elevation = 3.dp
    ) {
        Box(modifier = Modifier
            .height(150.dp)
            .padding(4.dp)) {
            Text( text = about.desc)
        }
    }
}

@Composable
fun AboutRow(about: AboutData, selectTask: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            selectTask(about.id)
        }
        .padding(8.dp)
    ) {
        Text(about.desc)
    }
}

