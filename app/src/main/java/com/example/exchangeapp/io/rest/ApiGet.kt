package com.example.exchangeapp.io.rest

import com.google.gson.annotations.SerializedName
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*
import kotlin.collections.HashMap

interface ApiGet{

    @GET("/latest")
    fun getLatestCurrency(@Query("base") base : String = "USD") : Single<RatesList>


    @GET("/history")
    fun getHistory(@Query("start_at") start : String,
                   @Query("end_at") end : String,
                   @Query("base") base : String,
                   @Query("symbols") symbols : String) : Single<HistoryList>

}



data class RatesList(
    @SerializedName("rates") val rates : HashMap<String,Double>,
    @SerializedName("base") val base : String,
    @SerializedName("date") val date : String
)

data class HistoryList(
    @SerializedName("rates") val rates : HashMap<String,HashMap<String,Double>>,
    @SerializedName("base") val base : String,
    @SerializedName("date") val date : String
)


