package jbar.cloudcoffee.controller

import com.google.firebase.database.FirebaseDatabase
import jbar.cloudcoffee.model.CartItem
import jbar.cloudcoffee.model.Order
import java.util.UUID

object OrderController {
    private val database = FirebaseDatabase.getInstance().getReference("orders")

    fun createOrder(userId: String, cartItems: List<CartItem>, onComplete: (Boolean) -> Unit) {
        // Generar un ID único para el pedido
        val orderId = UUID.randomUUID().toString()

        // Calcular el precio total del pedido
        val totalPrice = cartItems.sumOf { it.product.price * it.quantity }

        // Crear el objeto Order
        val order = Order(
            orderId = orderId,
            products = cartItems.map { it.product },
            totalPrice = totalPrice,
            userId = userId,
            status = "Pending"
        )

        // Guardar el pedido en la base de datos
        database.child(orderId).setValue(order)
            .addOnSuccessListener {
                onComplete(true) // Pedido guardado correctamente
            }
            .addOnFailureListener {
                onComplete(false) // Error al guardar el pedido
            }
    }
}
