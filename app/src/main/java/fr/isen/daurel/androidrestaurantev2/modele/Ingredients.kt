package fr.isen.daurel.androidrestaurantev2.modele

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Ingredients(

    @SerializedName("id") var id: String? = null,
    @SerializedName("id_shop") var idShop: String? = null,
    @SerializedName("name_fr") var nameFr: String? = null,
    @SerializedName("name_en") var nameEn: String? = null,
    @SerializedName("create_date") var createDate: String? = null,
    @SerializedName("update_date") var updateDate: String? = null,
    @SerializedName("id_pizza") var idPizza: String? = null

): Serializable