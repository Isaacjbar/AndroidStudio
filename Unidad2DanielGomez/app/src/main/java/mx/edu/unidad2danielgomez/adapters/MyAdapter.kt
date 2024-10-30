package mx.edu.unidad2danielgomez.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.unidad2danielgomez.R
import mx.edu.unidad2danielgomez.models.MyDataModel

class MyAdapter(private val dataList: List<MyDataModel>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.item_title)
        val description: TextView = itemView.findViewById(R.id.item_description)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.title.text = item.title
        holder.description.text = item.description
    }


    override fun getItemCount(): Int {
        return dataList.size
    }
}

