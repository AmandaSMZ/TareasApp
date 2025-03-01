package es.tierno.amanda.mz.tareasapp.data.modelo

import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareaCompleta

data class TareaConPrioridadModel(val titulo: String, val descripcion:String, val prioridad:String){
    public fun toTareaCompleta(): TareaCompleta{
        return TareaCompleta(this.titulo,this.descripcion,this.prioridad)
    }
}
