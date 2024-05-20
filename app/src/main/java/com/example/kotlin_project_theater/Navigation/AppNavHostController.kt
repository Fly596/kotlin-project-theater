package com.example.kotlin_project_theater.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.kotlin_project_theater.ui.home.ConfirmPurchaseScreen
import com.example.kotlin_project_theater.ui.home.GetTicketsScreen
import com.example.kotlin_project_theater.ui.home.HomeScreen
import com.example.kotlin_project_theater.ui.home.TicketOptionsScreen
import kotlinx.serialization.Serializable

@Composable
fun AppNavHostController() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreenA) {

        // Начальный экран со списком фильмов.
        composable<HomeScreenA> {
            HomeScreen(
                onMovieClick = { movieId -> navController.navigate(GetTicketsScreenB(movieId)) }
            )
        }

        // Экран с выбором театра.
        composable<GetTicketsScreenB> {
            val args = it.toRoute<GetTicketsScreenB>()

            GetTicketsScreen(args.selectedMovieId)
        }

        // Экран с выбором типа билета (взрослый/детский)
        composable<TicketTypeScreenC> { TicketOptionsScreen() }

        // Экран с подтверждением покупки.
        composable<ConfirmScreenD> {
            val args = it.toRoute<ConfirmScreenD>()

            ConfirmPurchaseScreen(onPurchaseClicked = args.onPurchaseClicked)
        }
    }
}

@Serializable object HomeScreenA

@Serializable data class GetTicketsScreenB(val selectedMovieId: Int)

@Serializable object TicketTypeScreenC

@Serializable data class ConfirmScreenD(val onPurchaseClicked: () -> Unit)
