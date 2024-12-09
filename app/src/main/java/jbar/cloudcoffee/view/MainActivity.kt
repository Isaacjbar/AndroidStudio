package jbar.cloudcoffee.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import jbar.cloudcoffee.R
import jbar.cloudcoffee.model.CartItem
import jbar.cloudcoffee.model.Product
import jbar.cloudcoffee.adapter.ProductAdapter
import jbar.cloudcoffee.controller.ProductController
import jbar.cloudcoffee.model.CartManager

class MainActivity : AppCompatActivity() {

    private lateinit var cartItemCountTextView: TextView
    private lateinit var recyclerView: RecyclerView

    private val cartItems = mutableListOf<CartItem>()
    private val productController = ProductController() // Inicializar el controlador

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity created")

        cartItemCountTextView = findViewById(R.id.cartItemCountTextView)
        recyclerView = findViewById(R.id.rvMenu)

        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener productos de Firebase
        fetchProducts()
        // Configurar el botón de carrito
        val fabCart: FloatingActionButton = findViewById(R.id.fab_cart)
        fabCart.setOnClickListener {
            // Abrir la actividad del carrito
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        // Actualizar la vista del carrito
        updateCartUI()
    }

    private fun fetchProducts() {
        Log.d(TAG, "Fetching products from controller...")
        productController.getProducts { productList ->
            runOnUiThread {
                if (productList.isNotEmpty()) {
                    Log.d(TAG, "${productList.size} products fetched successfully")
                    // Configurar el adaptador del RecyclerView con los productos obtenidos
                    recyclerView.adapter = ProductAdapter(productList) { product ->
                        onProductClicked(product)
                    }
                } else {
                    Log.w(TAG, "No products fetched. Check Firebase data.")
                }
            }
        }
    }

    private fun onProductClicked(product: Product) {
        Log.d(TAG, "Product clicked: ${product.name} (${product.id})")

        // Buscar si el producto ya está en el carrito
        val existingItem = cartItems.find { it.product.id == product.id }

        if (existingItem != null) {
            Log.d(TAG, "Product already in cart. Incrementing quantity.")
            existingItem.quantity++
        } else {
            Log.d(TAG, "Product added to cart.")
            cartItems.add(CartItem(product, 1))
        }

        // Actualizar la vista del carrito (por ejemplo, el número de productos en el icono)
        updateCartUI()
    }

    private fun updateCartUI() {
        val cartItemCount = CartManager.getCartItems().sumOf { it.quantity }
        cartItemCountTextView.text = cartItemCount.toString()
        Log.d(TAG, "Cart updated. Total items: $cartItemCount")
    }

    // Manejar el resultado de CartActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // Reiniciar el contador del carrito
            updateCartUI()
        }
    }
}
