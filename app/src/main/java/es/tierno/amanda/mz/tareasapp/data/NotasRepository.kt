package es.tierno.amanda.mz.tareasapp.data

import es.tierno.amanda.mz.tareasapp.data.servicioRest.NotasApi
import es.tierno.amanda.mz.tareasapp.data.servicioRest.NotasRespuesta
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NotasRepository @Inject constructor (){

    companion object{
        const val BASE_URL: String = "https://zenquotes.io/"
    }

    suspend fun getNota(): List<NotasRespuesta>? {
        val call = getRetrofit().create(NotasApi::class.java).getNota()
        val nota: List<NotasRespuesta>? = call.body()
        return nota
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}