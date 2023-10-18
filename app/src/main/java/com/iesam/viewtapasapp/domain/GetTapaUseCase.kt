package com.iesam.viewtapasapp.domain

import com.iesam.viewtapasapp.app.Either
import com.iesam.viewtapasapp.app.ErrorApp

class GetTapaUseCase (private val repository: TapaRepository) {

    operator fun invoke() : Either<ErrorApp, Tapa>  =  repository.get()

}