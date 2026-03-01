package dev.simonvar.gallery.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.simonvar.gallery.ui.swipe.SwipeScreen
import dev.simonvar.gallery.ui.trash.TrashScreen

@Composable
fun GalleryNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "swipe",
    ) {
        composable("swipe") {
            SwipeScreen(
                onNavigateToTrash = { navController.navigate("trash") },
            )
        }
        composable("trash") {
            TrashScreen(
                onBack = { navController.popBackStack() },
            )
        }
    }
}
