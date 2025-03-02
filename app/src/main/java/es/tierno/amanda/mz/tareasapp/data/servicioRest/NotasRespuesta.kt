package es.tierno.amanda.mz.tareasapp.data.servicioRest

import com.google.gson.annotations.SerializedName

data class NotasRespuesta(@SerializedName("q")val nota: String,
                          @SerializedName("a") val autor: String)
