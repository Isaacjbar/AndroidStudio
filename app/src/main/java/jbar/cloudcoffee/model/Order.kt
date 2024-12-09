package jbar.cloudcoffee.model

data class Order(
    val orderId: String,
    val products: List<Product>,
    val totalPrice: Double,
    val userId: String,
    val status: String
)
