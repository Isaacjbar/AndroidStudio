package jbar.cloudcoffee.model

object CartManager {
    private val cartItems = mutableListOf<CartItem>()

    fun getCartItems(): List<CartItem> = cartItems

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product))
        }
    }

    fun updateQuantity(productId: String, newQuantity: Int) {
        val item = cartItems.find { it.product.id == productId }
        if (item != null) {
            if (newQuantity <= 0) {
                cartItems.remove(item)
            } else {
                item.quantity = newQuantity
            }
        }
    }

    fun removeFromCart(productId: String) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun clearCart() {
        cartItems.clear()
    }
}
