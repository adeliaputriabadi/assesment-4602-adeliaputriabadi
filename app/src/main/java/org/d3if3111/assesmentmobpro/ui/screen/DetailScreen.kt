package org.d3if3111.assesmentmobpro.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.d3if3111.assesmentmobpro.R
import org.d3if3111.assesmentmobpro.ui.theme.AssesmentMobproTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen() {
    var keterangan by remember { mutableStateOf("") }
    var nominal by remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.tambah_list))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) {padding ->
        FormUang(
            title = keterangan,
            onTitleChange = { keterangan = it } ,
            rupiah = nominal,
            onRupiahChange = { nominal = it },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun FormUang(
    title: String, onTitleChange: (String) -> Unit,
    rupiah: String, onRupiahChange: (String) -> Unit,
    modifier: Modifier
) {
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
            modifier = Modifier.fillMaxSize()
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

    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    AssesmentMobproTheme {
        DetailScreen()
    }
}