package com.example.currencyconverter.presentaion.main_screen.common_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.domain.model.CurrencyRate


@Composable
fun BottomSheetContent(
    onItemClicked : (String) -> Unit,
    currencyList : List<CurrencyRate>
){

    LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp)){
        items(currencyList){currency ->

            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .clickable { onItemClicked(currency.code) }
            ) {

                Text(text ="${currency.code} : ${currency.name}", fontSize = 10.sp)



            }
        }
    }

}