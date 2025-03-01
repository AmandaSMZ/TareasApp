package es.tierno.amanda.mz.tareasapp.dominio.modelo

data class TareaCompleta (val titulo:String, val descripcion:String, val prioridad:String){
    override fun toString(): String {

        return "$titulo - $descripcion - $prioridad"

    }
}