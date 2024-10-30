package mx.edu.unidad2danielgomez

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class DestinoAdapter(
    private val context: Context,
    private val destinos: List<DestinoTuristico>
) : RecyclerView.Adapter<DestinoAdapter.DestinoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_destino, parent, false)
        return DestinoViewHolder(view)
    }

    // Método que se llama para enlazar los datos de un objeto 'DestinoTuristico' con las vistas del ViewHolder.
    override fun onBindViewHolder(holder: DestinoViewHolder, position: Int) {
        // Obtiene el destino turístico de la lista según la posición.
        val destino = destinos[position]

        // Asigna el nombre del destino a la vista 'nombreDestino'.
        holder.nombreDestino.text = destino.nombre

        // Asigna la descripción del destino a la vista 'descripcionDestino'.
        holder.descripcionDestino.text = destino.descripcion

        // Asigna la imagen del destino a la vista 'imagenDestino' utilizando el recurso de imagen.
        holder.imagenDestino.setImageResource(destino.imagenResId)
    }

    override fun getItemCount(): Int = destinos.size

    inner class DestinoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagenDestino: ImageView = itemView.findViewById(R.id.image_destino)
        val nombreDestino: TextView = itemView.findViewById(R.id.nombre_destino)
        val descripcionDestino: TextView = itemView.findViewById(R.id.description_destino)

    }
}
