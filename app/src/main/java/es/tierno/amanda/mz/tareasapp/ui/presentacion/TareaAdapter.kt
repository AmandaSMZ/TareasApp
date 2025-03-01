import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import es.tierno.amanda.mz.tareasapp.R
import es.tierno.amanda.mz.tareasapp.dominio.modelo.TareaCompleta

class TareaAdapter(private val mList: List<TareaCompleta>) : RecyclerView.Adapter<TareaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.tarea_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tarea = mList[position]


        holder.tvTitulo.text = tarea.titulo
        holder.tvDescripcion.text = tarea.descripcion
        holder.tvPrioridad.text = tarea.prioridad
        val color = when (tarea.prioridad) {
            "Importante" -> R.color.colorImportante
            "Urgente" -> R.color.colorUrgente
            "Prioridad Media" -> R.color.colorPrioridadMedia
            "Sin prisa" -> R.color.colorSinPrisa
            else -> R.color.black
        }

        holder.tvPrioridad.setTextColor(ContextCompat.getColor(holder.itemView.context, color))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTitulo)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val tvPrioridad: TextView = itemView.findViewById(R.id.tvPrioridad)
    }
}