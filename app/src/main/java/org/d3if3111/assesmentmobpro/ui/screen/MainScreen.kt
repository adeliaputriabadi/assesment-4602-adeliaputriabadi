package org.d3if3111.assesmentmobpro.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import org.d3if3111.assesmentmobpro.R



import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import org.d3if3111.assesmentmobpro.ui.theme.AssesmentMobproTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssesmentMobproTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar (
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF58A399),
                    titleContentColor = Color(0xFFFFFFFF)
                )
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var hargaAwal by remember { mutableStateOf("") }
    var persentaseDiskon by remember { mutableStateOf("") }

    val radioOptions = listOf(
        stringResource(id = R.string.harga_diskon),
        stringResource(id = R.string.total_bayar),

        )
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
            leadingIcon = { Text(text = "Rp")},
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
            trailingIcon = { Text(text = "%") },
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
                onClick = {},
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
                },
                modifier = Modifier.padding(top = 8.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp) ,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF58A399))
            ) {
                Text(text = stringResource(id = R.string.reset))
            }
        }
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
        MainScreen()
    }
}