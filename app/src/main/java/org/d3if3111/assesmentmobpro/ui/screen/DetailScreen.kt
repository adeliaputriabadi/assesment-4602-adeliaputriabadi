package org.d3if3111.assesmentmobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3111.assesmentmobpro.R
import org.d3if3111.assesmentmobpro.ui.theme.AssesmentMobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController) {
    var keterangan by remember { mutableStateOf("") }
    var nominal by remember { mutableStateOf("") }
    var selectedKategori by remember { mutableStateOf("Pendapatan") }




    Scaffold (
        topBar = {
            TopAppBar(
                navigationIcon = {
                                 IconButton(onClick = {navController.popBackStack() }) {
                                     Icon(
                                         imageVector = Icons.Filled.ArrowBack,
                                         contentDescription = stringResource(id = R.string.kembali),
                                         tint = MaterialTheme.colorScheme.primary
                                         )
                                 }
                },
                title = {
                    Text(text = stringResource(id = R.string.tambah_list))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                            )

                    }
                }

            )
        }
    ) {padding ->
        FormUang(
            title = keterangan,
            onTitleChange = { keterangan = it } ,
            rupiah = nominal,
            onRupiahChange = { nominal = it },
            selectedKategori = selectedKategori,
            onKategoriSelected = {selectedKategori = it},
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormUang(
    title: String, onTitleChange: (String) -> Unit,
    rupiah: String, onRupiahChange: (String) -> Unit,
    selectedKategori: String, onKategoriSelected: (String) -> Unit,
    modifier: Modifier
) {

    val radiOptions = listOf(
        stringResource(id = R.string.kategori_1),
        stringResource(id = R.string.kategori_2)
    )
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        OutlinedTextField(
            value = title ,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(id = R.string.keterangan)) },
            singleLine = false,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = rupiah ,
            onValueChange = { onRupiahChange(it) },
            label = { Text(text = stringResource(id = R.string.nominal)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Column (
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ){
            radiOptions.forEach { text ->
                KategoriOptions(
                    label = text,
                    isSelected = selectedKategori == text,
                    modifier = Modifier
                        .selectable(
                            selected = selectedKategori == text,
                            onClick = {onKategoriSelected(text)},
                            role = Role.RadioButton
                        )
                )
            }

        }
    }
}

@Composable
fun KategoriOptions(label: String, isSelected: Boolean, modifier: Modifier) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = isSelected, onClick = null )
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
fun DetailScreenPreview() {
    AssesmentMobproTheme {
        DetailScreen(rememberNavController())
    }
}