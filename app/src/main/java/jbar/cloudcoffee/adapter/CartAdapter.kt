package jbar.cloudcoffee.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import jbar.cloudcoffee.R
import jbar.cloudcoffee.model.CartItem

class CartAdapter(
    private val items: List<CartItem>,
    private val onAction: (String, String) -> Unit // Callback para manejar las acciones (incrementar, decrementar, eliminar)
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvCartItemName)
        val price: TextView = view.findViewById(R.id.tvCartItemPrice)
        val quantity: TextView = view.findViewById(R.id.tvCartItemQuantity)
        val increment: Button = view.findViewById(R.id.btnIncreaseQuantity)
        val decrement: Button = view.findViewById(R.id.btnDecreaseQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.name.text = item.product.name
        holder.price.text = "$${item.product.price * item.quantity}"
        holder.quantity.text = "x${item.quantity}"

        // Acciones de los botones
        holder.increment.setOnClickListener {
            onAction(item.product.id, "increment")
        }
        holder.decrement.setOnClickListener {
            onAction(item.product.id, "decrement")
        }
    }

    override fun getItemCount(): Int = items.size
}
