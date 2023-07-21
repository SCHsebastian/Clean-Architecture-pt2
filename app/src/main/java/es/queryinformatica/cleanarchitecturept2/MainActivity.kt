package es.queryinformatica.cleanarchitecturept2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import es.queryinformatica.cleanarchitecturept2.ui.theme.CleanArchitecturept2Theme
import es.queryinformatica.presentation_common.navigation.NavRoutes
import es.queryinformatica.presentation_posts.list.PostListScreen
import es.queryinformatica.presentation_posts.single.PostScreen
import es.queryinformatica.presentation_user.UserScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitecturept2Theme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavRoutes.Posts.route){
        composable(NavRoutes.Posts.route) {
            PostListScreen(hiltViewModel(), navController)
        }
        composable(
            route = NavRoutes.Post.route,
            arguments = NavRoutes.Post.args
        ){
            PostScreen(hiltViewModel(), NavRoutes.Post.fromEntry(it))
        }
        composable(
            route = NavRoutes.User.route,
            arguments = NavRoutes.User.args
        ){
            UserScreen(hiltViewModel(), NavRoutes.User.fromEntry(it))
        }
    }
}