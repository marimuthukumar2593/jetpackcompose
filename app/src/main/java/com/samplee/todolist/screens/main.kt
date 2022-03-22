package com.samplee.todolist.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.samplee.todolist.utils.Routes
import com.samplee.todolist.viewmodel.AboutViewmodel


/****************************************************************
 * Main() - main view of the application
 ****************************************************************/

@Composable
fun Main(viewmodel:AboutViewmodel){
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.HomeScreen.route ){

        composable(Routes.HomeScreen.route) {
            Home(
                viewmodel,
                navController)
            {
                /* naviagate to show the edit view of  corresponding about id details */
                println("About clicked position  == $it")

                navController.navigate("${Routes.AboutScreen.route}/$it")
            }
        }

        composable(
            route = Routes.AboutScreen.routeWithArgument,
            arguments = listOf(
                navArgument(Routes.AboutScreen.argument) { type = NavType.IntType }
            )
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getInt(Routes.AboutScreen.argument) ?: return@composable
//            val id:Int? = backStackEntry.arguments?.getInt(Routes.AboutScreen.argument)

            println("About id  == $id")
            id?.let { viewmodel.fetchById(id) }

            AboutDetails(viewmodel) {
                navController.navigateUp()
            }
            println("About is called")

        }
    }

}

