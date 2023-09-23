package com.example.currencyconverter.presentaion.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R


@Composable
fun MainScreen() {

    val keys = listOf("1","2","3","4","5","6","7","8","9",".","0","C")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            modifier = Modifier.fillMaxWidth(),
             text = "Coin Rate",
             style = MaterialTheme.typography.titleMedium,
             fontFamily = FontFamily.SansSerif,
             fontSize = 40.sp,
             textAlign = TextAlign.Center,
             fontWeight = FontWeight.Bold
            )
          Box(contentAlignment = Alignment.CenterStart){
            Column {
                Card(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {

                        CurrencyRow(
                            modifier = Modifier.fillMaxWidth(),
                            currencyCode = "INR" ,
                            currencyName = "Indian RS",
                            onDropDownItemClicked = {

                            }
                        )

                        Text(text = "80.234" , fontSize = 40.sp)



                    }

                }
                
                Spacer(modifier = Modifier.height(12.dp))
                Card(modifier = Modifier
                    .fillMaxWidth()
                ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(text = "80.234" , fontSize = 40.sp)

                        CurrencyRow(
                            modifier = Modifier.fillMaxWidth(),
                            currencyCode = "INR" ,
                            currencyName = "Indian RS",
                            onDropDownItemClicked = {

                            }
                        )




                    }

                }
            }
            Box(modifier = Modifier
                .padding(start = 40.dp)
                .clip(CircleShape)
                .clickable { }
                .background(color = MaterialTheme.colorScheme.background)
            ){
                Icon(painter = painterResource(id = R.drawable.baseline_sync_24),
                    contentDescription = "Swap Currency",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 35.dp),
            columns = GridCells.Fixed(3)
        ){
            items(keys){key ->

                KeyboardButton(modifier = Modifier.aspectRatio(1f),
                    key = key,
                    backgroundColor = if (key == "C") MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant,
                    onClick = {})
            }

        }


    }
}