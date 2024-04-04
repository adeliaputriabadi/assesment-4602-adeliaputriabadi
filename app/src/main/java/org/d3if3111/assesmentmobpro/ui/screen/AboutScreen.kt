package org.d3if3111.assesmentmobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3111.assesmentmobpro.R
import org.d3if3111.assesmentmobpro.model.Kalkulator
import org.d3if3111.assesmentmobpro.ui.theme.AssesmentMobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar (
                navigationIcon = {
                                 IconButton(onClick = {navController.popBackStack()}) {
                                     Icon(
                                         imageVector = Icons.Filled.ArrowBack,
                                         contentDescription = stringResource(R.string.kembali),
                                         tint = MaterialTheme.colorScheme.primary
                                     )
                                 }
                },
                title = {
                    Text(text = stringResource(id = R.string.tentang_aplikasi))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF58A399),
                    titleContentColor = Color(0xFFFFFFFF)
                )
            )
        }
    ) { padding ->
        val kalkulator = getData().first()
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

            ){

            Image(
                painter = painterResource(id = kalkulator.imageResID),
                contentDescription = stringResource(id = R.string.gambar),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(132.dp)

            )
            Text(
                text = kalkulator.nama,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 16.dp)

            )


        Text(
            text = stringResource(id = R.string.copyright),
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        )

    }
}
}

    private fun getData() : List<Kalkulator> {
       return listOf(
           Kalkulator("Kalkulator Diskon", R.drawable.kalkulator)
       )

    }

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AboutScreenPreview() {
    AssesmentMobproTheme {
        AboutScreen(rememberNavController())
    }
}