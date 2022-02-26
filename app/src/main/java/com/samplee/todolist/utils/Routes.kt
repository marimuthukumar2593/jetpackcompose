package com.samplee.todolist.utils


/****************************************************************
 * Routes() - this class for routes to navigation
 ****************************************************************/
sealed class Routes(val route: String) {
    object HomeScreen: Routes("home")
    object AboutScreen: Routes("about"){
        const val routeWithArgument: String = "about/{aboutId}"
        const val argument: String = "aboutId"

        const val routeWithoutArgument: String = "about/0"
    }
}