package es.tierno.amanda.mz.tareasapp.data.servicioRest

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface NotasApi {
    @GET("https://zenquotes.io/api/random")
    suspend fun getNota(): Response<List<NotasRespuesta>>
}