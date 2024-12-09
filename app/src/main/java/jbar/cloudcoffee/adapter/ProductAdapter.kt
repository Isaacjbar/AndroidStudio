package jbar.cloudcoffee.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import jbar.cloudcoffee.model.Product
import jbar.cloudcoffee.R
import jbar.cloudcoffee.view.ProductDetailActivity


class ProductAdapter(
    private val productList: List<Product>,
    private val onProductClick: (Product) -> Unit // El callback para agregar al carrito
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val productName: TextView = itemView.findViewById(R.id.tvProductName)
        val productDescription: TextView = itemView.findViewById(R.id.tvProductDescription)
        val productPrice: TextView = itemView.findViewById(R.id.tvProductPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productDescription.text = product.description
        holder.productPrice.text = "$${product.price}"

        // Cargar imagen con Glide
        Glide.with(holder.itemView.context)
            .load(product.imageUrl)
            .placeholder(R.drawable.placeholder_image) // Imagen de carga
            .into(holder.productImage)

        // Configurar el clic para ver detalles del producto
        holder.itemView.setOnClickListener {
            // Agregar al carrito
            onProductClick(product)

            // Abrir la actividad de detalle del producto
            val intent = Intent(holder.itemView.context, ProductDetailActivity::class.java).apply {
                putExtra("product_id", product.id)
                putExtra("product_name", product.name)
                putExtra("product_description", product.description)
                putExtra("product_price", product.price)
                putExtra("product_image_url", product.imageUrl)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = productList.size
}
