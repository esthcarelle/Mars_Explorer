package com.mine.marsexplorer.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mine.design.navigation.Screen
import com.mine.utils.Constants.DETAILS_ROUTE
import com.mine.utils.Constants.IMAGE
import com.mine.utils.Constants.LANDING_DATE
import com.mine.utils.Constants.LAUNCH_DATE
import com.mine.utils.Constants.MINUS_THREE_HUNDRED
import com.mine.utils.Constants.NAME
import com.mine.utils.Constants.THREE_HUNDRED
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MarsPhotosNavigation(toggleTheme: () -> Unit) {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController, startDestination = Screen.Home.route) {
        composable(
            Screen.Home.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(THREE_HUNDRED))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { MINUS_THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(THREE_HUNDRED))
            },
        ) {
            MarsPhotosList(navController, hiltViewModel(), toggleTheme)
        }
        composable(
            "${Screen.Details.route}${DETAILS_ROUTE}",
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeIn(animationSpec = tween(THREE_HUNDRED))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { THREE_HUNDRED },
                    animationSpec = tween(
                        durationMillis = THREE_HUNDRED,
                        easing = FastOutSlowInEasing
                    )
                ) + fadeOut(animationSpec = tween(THREE_HUNDRED))
            },
            arguments = listOf(navArgument(IMAGE) { type = NavType.StringType },
                navArgument(NAME) { type = NavType.StringType },
                navArgument(LAUNCH_DATE) { type = NavType.StringType },
                navArgument(LANDING_DATE) { type = NavType.StringType })
        ) {
            MarsPhotoDetails(
                navController,
                hiltViewModel(),
                it.arguments?.getString(IMAGE) ?: "",
                it.arguments?.getString(NAME) ?: "",
                it.arguments?.getString(LAUNCH_DATE) ?: "",
                it.arguments?.getString(LANDING_DATE) ?: ""
            )
        }
    }
}