package com.iesam.viewtapasapp.data.remote

import com.iesam.viewtapasapp.app.Either
import com.iesam.viewtapasapp.app.ErrorApp
import com.iesam.viewtapasapp.app.right
import com.iesam.viewtapasapp.domain.Tapa

class ApiMockRemoteDataSource {

    fun getTapaMock () : Either<ErrorApp, Tapa> {
        return Tapa ("1","Albóndiga de faisán en escabeche con caldo clarificado de gambón salvaje", "Vermueria el Atrio", "Total: 0 Ptos." , "Media: 0.0").right()
    }
}