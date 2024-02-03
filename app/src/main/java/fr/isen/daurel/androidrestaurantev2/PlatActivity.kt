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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.daurel.androidrestaurantev2.modele.DataResult
import fr.isen.daurel.androidrestaurantev2.modele.Items
import fr.isen.daurel.androidrestaurantev2.ui.theme.AndroidRestauranteV2Theme
import org.json.JSONObject
import java.io.Serializable

class PlatActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val nomCategorie = intent.getStringExtra("dish") ?: ""

        setContent {
            AndroidRestauranteV2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val itemsState = remember { // ce souvient de la variable
                        mutableStateListOf<Items>()
                    }
                    fetchdata(nomCategorie,itemsState)
                    AllDishes(itemsState, nomCategorie, ::goToDetail)
                }
            }
        }
    }

    private fun goToDetail(dish: Items) {
        Toast.makeText(this, dish.nameFr, Toast.LENGTH_LONG).show()
        val intent = Intent(this, DetailDishActivity::class.java)
        intent.putExtra("dish", dish as Serializable)
        startActivity(intent)
    }

    private fun fetchdata(nomCategorie: String, itemsState: SnapshotStateList<Items>) {
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val jsonObject = JSONObject()
        jsonObject.put("id_shop","1")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonObject,
            {
                Log.d("PlatActivity","les données brutes : $it")
                // Utilisez GSON pour convertir la réponse JSON en objets de modèle
                val result = Gson().fromJson(it.toString(), DataResult::class.java)

                // Filtrer les données en fonction de la catégorie précédemment sélectionnée
                val filteredItems = result.data.find { it.nameFr == nomCategorie }?.items ?: emptyList()
                Log.d("PlatActivity","les données filtrés : $filteredItems")
                itemsState.addAll(filteredItems)
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
fun AllDishes(itemsState: SnapshotStateList<Items> , nomCategorie: String, goToDetail: (Items) -> Unit) {
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
                items(itemsState) {
                    DishCard(it, goToDetail)
                }
            }
        }
    }
}

