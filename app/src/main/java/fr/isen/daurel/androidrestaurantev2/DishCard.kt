package fr.isen.daurel.androidrestaurantev2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.isen.daurel.androidrestaurantev2.modele.Items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishCard(dish: Items, goToDetail: (Items) -> Unit) {
    Card(
        onClick = {goToDetail(dish) },
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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(dish.images.random())
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.robot),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(70.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    dish.nameFr ?:"",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                Text(
                    text = (dish.prices.first().price  ?:"--") + "$",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }
        }
    }
}
