package fr.isen.daurel.androidrestaurantev2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.isen.daurel.androidrestaurantev2.ui.theme.AndroidRestauranteV2Theme
import org.json.JSONObject


class PlatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nomCategorie = intent.getStringExtra("dish") ?: ""
        fetchdata()
        setContent {
            AndroidRestauranteV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AllDishes(dishes, nomCategorie, ::goToDetail)
                }
            }
        }
    }

    private fun goToDetail(dishName: String) {
        Toast.makeText(this, dishName, Toast.LENGTH_LONG).show()
        val intent = Intent(this, DetailDishActivity::class.java)
        intent.putExtra("dish", dishName)
        startActivity(intent)
    }
    
    private fun fetchdata() {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            {
                Log.d("PlatActivity","les données en brut : $it")
            },
            {
                Log.e("PlatActivity","error : $it")
            })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(jsonObjectRequest)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllDishes(dishList: List<Dish>, nomCategorie: String, goToDetail: (String) -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = nomCategorie, fontWeight = FontWeight.ExtraBold)
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
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 30.dp)
            ) {
                items(dishList) {
                    DishCard(dish = it, goToDetail)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EachRowContact() {
    AndroidRestauranteV2Theme {
        //AllDishes(dishes, "Plats", )
    }
}