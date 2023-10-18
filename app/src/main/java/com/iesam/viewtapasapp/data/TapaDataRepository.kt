package com.iesam.viewtapasapp.data

import com.iesam.viewtapasapp.app.Either
import com.iesam.viewtapasapp.app.ErrorApp
import com.iesam.viewtapasapp.app.right
import com.iesam.viewtapasapp.data.local.XmlLocalDataSource
import com.iesam.viewtapasapp.data.remote.ApiMockRemoteDataSource
import com.iesam.viewtapasapp.domain.Tapa
import com.iesam.viewtapasapp.domain.TapaRepository

class TapaDataRepository (private val xmlLocalDataSource: XmlLocalDataSource, private val apiMockRemoteDataSource: ApiMockRemoteDataSource): TapaRepository {
    override fun save(tapa: Tapa): Either<ErrorApp, Boolean> {
        xmlLocalDataSource.saveTapa(tapa)
        return true.right()

    }

    override fun get(): Either<ErrorApp, Tapa> {

        val tapa = xmlLocalDataSource.getTapa()
        if (tapa.isRight() && tapa.get().id != "") return tapa.get().right()
        else{
            apiMockRemoteDataSource.getTapaMock().map {
                save(it)
            }
            return xmlLocalDataSource.getTapa()
        }
    }


}