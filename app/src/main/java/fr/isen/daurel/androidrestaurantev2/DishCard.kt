package fr.isen.daurel.androidrestaurantev2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishCard(dish: Dish, goToDetail:(String) -> Unit) {
    Card(
        onClick = {goToDetail(dish.name) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .padding(horizontal = 12.dp)
        ) {
            Image(
                painter = painterResource(id = dish.image),
                contentDescription = dish.name,
                modifier = Modifier
                    .size(40.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    dish.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
                Text(
                    dish.desc,
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                )
            }
            Text(
                dish.prix.toString() + "€",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}