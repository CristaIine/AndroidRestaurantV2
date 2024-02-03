package fr.isen.daurel.androidrestaurantev2.modele

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataResult(

    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()

) : Serializable