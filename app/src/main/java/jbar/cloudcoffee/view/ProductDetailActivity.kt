package jbar.cloudcoffee.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import jbar.cloudcoffee.R
import android.widget.Button
import jbar.cloudcoffee.model.CartManager
import jbar.cloudcoffee.model.Product

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Obtener los datos del producto desde el intent
        val productId = intent.getStringExtra("product_id") ?: return
        val productName = intent.getStringExtra("product_name") ?: return
        val productDescription = intent.getStringExtra("product_description") ?: return
        val productPrice = intent.getDoubleExtra("product_price", 0.0)
        val productImageUrl = intent.getStringExtra("product_image_url") ?: ""

        // Configurar los elementos de la UI
        val addToCartButton: Button = findViewById(R.id.btnAddToCart)

        // Configurar la lógica del botón "Agregar al carrito"
        addToCartButton.setOnClickListener {
            val product = Product(productId, productName, productDescription, productPrice, productImageUrl)
            CartManager.addToCart(product)
            finish() // Regresar a la pantalla anterior
        }
    }
}
