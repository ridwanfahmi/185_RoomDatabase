package com.example.kotlinksp.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlinksp.view.DetailSiswaScreen
import com.example.kotlinksp.view.EditSiswaScreen
import com.example.kotlinksp.view.EntrySiswaScreen
import com.example.kotlinksp.view.HomeScreen
import com.example.kotlinksp.view.route.DestinasiDetailSiswa
import com.example.kotlinksp.view.route.DestinasiDetailSiswa.itemIdArg
import com.example.kotlinksp.view.route.DestinasiEditSiswa
import com.example.kotlinksp.view.route.DestinasiEntry
import com.example.kotlinksp.view.route.DestinasiHome


@Composable
fun SiswaApp(navController: NavHostController= rememberNavController(), modifier: Modifier){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(navController=navController, startDestination = DestinasiHome.route, modifier = Modifier)
    {
        composable(DestinasiHome.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                navigateToItemUpdate = { navController.navigate("${DestinasiDetailSiswa.route}/${it}") },

            )
        }
        composable(DestinasiEntry.route) {
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            route = DestinasiDetailSiswa.routeWithArgs,
            arguments = listOf(navArgument(DestinasiDetailSiswa.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            DetailSiswaScreen(
                // ... kode lain ...

                // PERBAIKAN DI SINI: Gunakan DestinasiEditSiswa
                navigateToEditItem = { navController.navigate("${DestinasiEditSiswa.route}/$it") },

                navigateBack = { navController.navigateUp() }
            )
        }

        composable(
            route = DestinasiEditSiswa.routeWithArgs,
            arguments = listOf(
                navArgument(itemIdArg) {
                    type = NavType.IntType
                    defaultValue = -1 // Pilihan: Menambahkan defaultValue untuk keamanan
                }
            )
        ) { backStackEntry -> // backStackEntry diperlukan jika Anda ingin mengakses argumen secara langsung di sini
            EditSiswaScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }

            )
        }
    }
}