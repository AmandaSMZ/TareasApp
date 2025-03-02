package es.tierno.amanda.mz.tareasapp.data

import es.tierno.amanda.mz.tareasapp.data.modelo.NotasModel
import es.tierno.amanda.mz.tareasapp.data.servicioRest.NotasApi
import es.tierno.amanda.mz.tareasapp.data.servicioRest.NotasRespuesta
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NotasRepository @Inject constructor (){

    companion object{
        const val BASE_URL: String = "https://zenquotes.io/"
    }

    suspend fun getNota(): NotasModel {
        val call = getRetrofit().create(NotasApi::class.java).getNota()
        val resul: List<NotasRespuesta>? = call.body()
        if (resul.isNullOrEmpty()){
            throw Exception("No se pudo obtener la nota")
        }else{
            val nota = resul[0]
            return NotasModel(nota.nota,nota.autor)
        }
    }


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}