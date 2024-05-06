package org.d3if3111.assesmentmobpro.navigation

import org.d3if3111.assesmentmobpro.ui.screen.KEY_ID_UANG

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_UANG}") {
        fun withId(id: Long) = "detailScreen/$id"
    }
}