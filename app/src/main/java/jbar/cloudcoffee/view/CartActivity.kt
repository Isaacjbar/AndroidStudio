package jbar.cloudcoffee.view

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jbar.cloudcoffee.R
import jbar.cloudcoffee.adapter.CartAdapter
import jbar.cloudcoffee.controller.OrderController
import jbar.cloudcoffee.model.CartManager

class CartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        // Obtener el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvCart)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Obtener los ítems del carrito desde CartManager
        val cartItems = CartManager.getCartItems()

        // Configurar el adaptador del RecyclerView
        val cartAdapter = CartAdapter(cartItems) { productId, action ->
            when (action) {
                "increment" -> {
                    CartManager.updateQuantity(productId, cartItems.find { it.product.id == productId }!!.quantity + 1)
                }
                "decrement" -> {
                    CartManager.updateQuantity(productId, cartItems.find { it.product.id == productId }!!.quantity - 1)
                }
                "remove" -> {
                    CartManager.removeFromCart(productId)
                }
            }
            // Notificar cambios al adaptador después de realizar las modificaciones
            recyclerView.adapter?.notifyDataSetChanged()
        }
        recyclerView.adapter = cartAdapter
        // Configurar el botón "Finalizar compra"
        val btnCheckout: Button = findViewById(R.id.btnCheckout)
        btnCheckout.setOnClickListener {
            if (cartItems.isNotEmpty()) {
                // Simular un ID de usuario
                val userId = "user123"

                // Crear el pedido en la base de datos
                OrderController.createOrder(userId, cartItems) { success ->
                    if (success) {
                        Toast.makeText(this, "Pedido creado exitosamente", Toast.LENGTH_SHORT).show()

                        // Vaciar el carrito
                        CartManager.clearCart()

                        // Notificar a MainActivity para que reinicie el contador
                        setResult(Activity.RESULT_OK)

                        recyclerView.adapter?.notifyDataSetChanged()

                        // Cerrar la actividad
                        finish()
                    } else {
                        Toast.makeText(this, "Error al crear el pedido", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
