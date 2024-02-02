package fr.isen.daurel.androidrestaurantev2.modele

import com.google.gson.annotations.SerializedName

data class DataResult(

    @SerializedName("data") var data: ArrayList<Data> = arrayListOf()

)