package com.example.mvvmapp.data.model

import com.google.gson.annotations.SerializedName

data class QuoteModel (@SerializedName("quote") val quote: String, val author:String)