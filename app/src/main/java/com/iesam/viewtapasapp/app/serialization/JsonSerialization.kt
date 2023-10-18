package com.iesam.viewtapasapp.app.serialization

interface JsonSerialization {
    fun <T> toJson(obj: T, typeClass: Class<T>): String
    fun <T> fromJson(json : String, typeClass : Class<T>) : T


}