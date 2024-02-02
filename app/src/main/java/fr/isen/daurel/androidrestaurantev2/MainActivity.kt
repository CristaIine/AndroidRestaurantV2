package fr.isen.daurel.androidrestaurantev2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.isen.daurel.androidrestaurantev2.ui.theme.AndroidRestauranteV2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidRestauranteV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Greeting(::onMenuClick)
                }
            }
        }
    }

    private fun onMenuClick(menu: String) {
        Toast.makeText(this, menu, Toast.LENGTH_LONG).show()
        val intent = Intent(this, PlatActivity::class.java)
        intent.putExtra("dish", menu)
        startActivity(intent)
    }
}

@Composable
fun Greeting(onMenuClick: (String) -> Unit) {
    val colorStops = arrayOf(
        0.0f to Color.Yellow,
        0.2f to Color.Red,
        1f to Color.Blue
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colorStops = colorStops))
    )
    {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(10.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Image(
                painter = painterResource(R.drawable.robot),
                contentDescription = "Logo entreprise",
                modifier = Modifier

                    .size(100.dp)
                    .clip(CircleShape)
                    .border(2.dp, Brush.verticalGradient(colorStops = colorStops), CircleShape)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.width(30.dp))
            Text(
                "Welcome to the \n  Bar à droid",
                fontSize = 24.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(150.dp))
        // Nouvelle colonne avec les boutons "Entrée", "Plat" et "Dessert"
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                eachButton("Entrée", onMenuClick)

                Spacer(modifier = Modifier.height(5.dp)) // Ajout d'un espace vertical

                eachButton("Plats", onMenuClick)

                Spacer(modifier = Modifier.height(5.dp)) // Ajout d'un espace vertical

                eachButton("Desserts", onMenuClick)
            }
        }
    }
}

@Composable
fun eachButton(name: String, onClick: (String) -> Unit) {
    Column(
        Modifier.padding(20.dp)
    ) {
        OutlinedButton(
            onClick = {onClick(name)},
            border = BorderStroke(3.dp, Color.White),
            shape = RoundedCornerShape(40), // = 50% percent
            // or shape = CircleShape
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
        ) {
            Text(text = "$name", fontSize = 30.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidRestauranteV2Theme {
        Greeting() {}
    }
}

