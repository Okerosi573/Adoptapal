package com.judy.adoptapal.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.judy.adoptapal.ui.theme.screens.adoptable.PetCategoryTabs
import com.judy.adoptapal.ui.theme.screens.adoptionform.AdoptionForm
import com.judy.adoptapal.ui.theme.screens.adoptionform.AdoptionForm_Screen
import com.judy.adoptapal.ui.theme.screens.adoptionform.SuccessScreen
import com.judy.adoptapal.ui.theme.screens.home.Home_Screen
import com.judy.adoptapal.ui.theme.screens.login.Login_Screen
import com.judy.adoptapal.ui.theme.screens.petlistings.Catlist_Screen
import com.judy.adoptapal.ui.theme.screens.petlistings.Doglist_Screen
import com.judy.adoptapal.ui.theme.screens.petlistings.Rabbitlist_Screen
import com.judy.adoptapal.ui.theme.screens.register.Register_Screen
import com.judy.adoptapal.ui.theme.screens.splash.Splash_Screen
import com.judy.adoptapal.ui.theme.screens.userprofile.Userprofile_Screen

@Composable
fun AppNavHost(navController: NavHostController= rememberNavController(),
               startDestination: String= ROUTE_SPLASH) {
    NavHost(
        navController = navController,
        modifier = Modifier,
        startDestination = startDestination
    ) {
        composable(ROUTE_SPLASH) { Splash_Screen(navController) }
        composable(ROUTE_LOGIN) { Login_Screen(navController) }
        composable(ROUTE_REGISTER) { Register_Screen(navController) }
        composable(ROUTE_HOME) { Home_Screen(navController) }
        composable(ROUTE_USERPROFILE) { Userprofile_Screen(navController) }
        composable(ROUTE_ADOPTIONFORM) { AdoptionForm_Screen(navController) }
        composable(ROUTE_CATLIST) { Catlist_Screen(navController) }
        composable(ROUTE_DOGLIST) { Doglist_Screen(navController) }
        composable(ROUTE_RABBITLIST) { Rabbitlist_Screen(navController) }
        composable(ROUTE_SUCCESS){ SuccessScreen(navController) }

        composable("PetSection/{type}") { backStackEntry ->
            val petType = backStackEntry.arguments?.getString("type") ?: "Dog"
            PetCategoryTabs(
                selectedCategory = "Dog", // or petType if dynamic
                onInterestedClick = { petId ->
                    navController.navigate("adoption_form/$petId")
                }
            )

        }
        composable("adoption_form/{petId}") { backStackEntry ->
            val petId = backStackEntry.arguments?.getString("petId")
            AdoptionForm_Screen(navController = navController, petId = petId)
        }



    }
}