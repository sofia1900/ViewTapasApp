package com.iesam.viewtapasapp.domain

import com.iesam.viewtapasapp.app.Either
import com.iesam.viewtapasapp.app.ErrorApp

interface TapaRepository {

    fun save (tapa : Tapa ) : Either<ErrorApp, Boolean>

    fun get () : Either<ErrorApp, Tapa>
}