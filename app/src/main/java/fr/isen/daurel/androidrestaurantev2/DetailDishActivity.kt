package fr.isen.daurel.androidrestaurantev2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import fr.isen.daurel.androidrestaurantev2.modele.DataResult
import fr.isen.daurel.androidrestaurantev2.modele.Items
import fr.isen.daurel.androidrestaurantev2.ui.theme.AndroidRestauranteV2Theme
import fr.isen.daurel.androidrestaurantev2.ui.theme.getSerializable

class DetailDishActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailledDish = intent.getSerializable("dish", Items::class.java)
        detailledDish.images.addAll(detailledDish.images)

        setContent {
            AndroidRestauranteV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DishScreen(detailledDish)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DishScreen(dish: Items) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = dish.nameFr ?:"", fontWeight = FontWeight.ExtraBold)
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 30.dp)
            ) {
                items(dish.images) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(it)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.robot),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .height(200.dp)
                            .background(Color.LightGray)
                            .padding(end = 4.dp)
                    )
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DetailDishActivityPreview() {

    val dish = Items(
        id = "1",
        nameFr = "Boeuf",
        images = arrayListOf("https://www.boeuf-irlandais.fr/wp-content/uploads/sites/10/2021/07/boeuf-irlandais-organic_beef_farm-mobile.jpg",
        "https://www.shutterstock.com/image-vector/black-brown-bull-male-farm-600nw-2270855067.jpg",
        "https://observatoire-des-aliments.fr/wp-content/uploads/2013/01/le-boeuf-1.jpg")
        )

    AndroidRestauranteV2Theme {

        DishScreen(dish)
    }
}

