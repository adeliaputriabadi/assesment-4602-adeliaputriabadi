package org.d3if3111.assesmentmobpro.navigation

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
}