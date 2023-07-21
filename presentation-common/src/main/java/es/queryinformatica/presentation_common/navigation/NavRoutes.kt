package es.queryinformatica.presentation_common.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_POSTS = "posts"
private const val ROUTE_POST = "posts/%s"
private const val ROUTE_USER = "users/%s"
private const val ARG_POST_ID = "postId"
private const val ARG_USER_ID = "userId"

sealed class NavRoutes (
    val route: String,
    val args: List<NamedNavArgument> = emptyList()
        ){
    object Posts : NavRoutes(route = String.format(ROUTE_POSTS))

    object Post : NavRoutes(route = String.format(ROUTE_POST, "{$ARG_POST_ID}"), args = listOf(navArgument(ARG_POST_ID) { type = NavType.LongType })){
        fun routeForPost(postInput: PostInput) = String.format(ROUTE_POST, postInput.postId)
        fun fromEntry(entry: NavBackStackEntry): PostInput {
            return PostInput(postId = entry.arguments?.getLong(ARG_POST_ID) ?: 0L)
        }
    }

    object User : NavRoutes(route = String.format(ROUTE_USER, "{$ARG_USER_ID}"), args = listOf(navArgument(ARG_USER_ID) { type = NavType.LongType })) {
        fun routeForUser(userInput: UserInput) = String.format(ROUTE_USER, userInput.userId)
        fun fromEntry(entry: NavBackStackEntry): UserInput {
            return UserInput(entry.arguments?.getLong(ARG_USER_ID) ?: 0L)
        }
    }

}