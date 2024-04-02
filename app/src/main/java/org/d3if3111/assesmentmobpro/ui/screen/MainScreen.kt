package org.d3if3111.assesmentmobpro.ui.screen


import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3111.assesmentmobpro.R
import org.d3if3111.assesmentmobpro.navigation.Screen
import org.d3if3111.assesmentmobpro.ui.theme.AssesmentMobproTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController : NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF58A399),
                    titleContentColor = Color(0xFFFFFFFF)
                ),
                actions = {
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.About.route)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )

                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var hargaAwal by remember { mutableStateOf("") }
    var hargaAwalError by remember { mutableStateOf(false) }

    var persentaseDiskon by remember { mutableStateOf("") }
    var persentaseDiskonError by remember { mutableStateOf(false) }


    val radioOptions = listOf(
        stringResource(id = R.string.harga_diskon),
        stringResource(id = R.string.total_bayar),

        )

    var harga by rememberSaveable { mutableFloatStateOf(0f) }


    var kategori by remember { mutableStateOf(radioOptions[0]) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.kalkulator_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = hargaAwal,
            onValueChange = { hargaAwal = it },
            label = { Text(text = stringResource(R.string.harga_awal)) },
            isError = hargaAwalError,
            leadingIcon = { IconPicker(hargaAwalError,"Rp") },
            supportingText = { ErrorHint(hargaAwalError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = persentaseDiskon,
            onValueChange = { persentaseDiskon = it },
            label = { Text(text = stringResource(R.string.persentase_diskon)) },
            isError = persentaseDiskonError,
            trailingIcon = { IconPicker(persentaseDiskonError, "%") },
            supportingText = { ErrorHint(persentaseDiskonError)},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                KategoriPilihan(
                    label = text,
                    isSelected = kategori == text,
                    modifier = Modifier
                        .selectable(
                            selected = kategori == text,
                            onClick = { kategori = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    hargaAwalError = (hargaAwal == "" || hargaAwal == "0")
                    persentaseDiskonError = (persentaseDiskon == "" || persentaseDiskon == "0")
                    if (hargaAwalError || persentaseDiskonError) return@Button

                    harga = hitungRumus(
                        hargaAwal.toFloat(),
                        persentaseDiskon.toFloat(),
                        kategori == radioOptions[0]
                    )



                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF58A399))
            ) {
                Text(text = stringResource(R.string.hitung))
            }
            Button(
                onClick = {
                    hargaAwal = ""
                    persentaseDiskon = ""
                    harga = 0f
                    //rumusTotal = 0f
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp) ,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF58A399))
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
        }
        if (harga != 0f ){
            Divider(
                modifier =  Modifier.padding(vertical = 16.dp),
                thickness = 1.dp
            )


           Text(text = stringResource(id = R.string.rumus_total, harga))
        }

    }

}

@Composable
fun IconPicker(isError: Boolean, unit : String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)

    }
    else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(id = R.string.input_invalid))
    }
}



private fun hitungRumus(hargaAwal: Float, persentaseDiskon: Float, isDiskon:Boolean): Float {
    return if(isDiskon) {
        hargaAwal * (persentaseDiskon / 100)
    }
    else {
        hargaAwal - (hargaAwal * (persentaseDiskon /100))
    }
}






    @Composable
    fun KategoriPilihan(label: String, isSelected: Boolean, modifier: Modifier) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            RadioButton(selected = isSelected, onClick = null)
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }







@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    AssesmentMobproTheme {
        MainScreen(rememberNavController())
    }
}