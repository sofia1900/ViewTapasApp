package com.iesam.viewtapasapp.data.local

import android.content.Context
import com.google.gson.Gson
import com.iesam.viewtapasapp.app.Either
import com.iesam.viewtapasapp.app.ErrorApp
import com.iesam.viewtapasapp.app.left
import com.iesam.viewtapasapp.app.right
import com.iesam.viewtapasapp.app.serialization.GsonSerialization
import com.iesam.viewtapasapp.app.serialization.JsonSerialization
import com.iesam.viewtapasapp.domain.Tapa

class XmlLocalDataSource (private val context : Context, private val serialization: JsonSerialization){

    private val sharedPref = context.getSharedPreferences("tapas", Context.MODE_PRIVATE)



    fun getTapa () : Either<ErrorApp, Tapa> {
        return try{
            serialization
                .fromJson(sharedPref.getString("1", "{}")!!, Tapa::class.java)
                .right()
        }catch (ex : Exception){
            ErrorApp.UnknowError.left()
        }
    }

    fun saveTapa (tapa : Tapa) : Either<ErrorApp, Boolean> {
        return try{
            with(sharedPref.edit()){
                val jsonConversation = serialization.toJson(tapa, Tapa::class.java)
                putString(tapa.id, jsonConversation)
                apply()
            }
            true.right()
        }catch (ex : Exception){
            return ErrorApp.UnknowError.left()
        }
    }

}