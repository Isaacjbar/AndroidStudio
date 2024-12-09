package jbar.cloudcoffee.controller

import android.util.Log
import com.google.firebase.database.*
import jbar.cloudcoffee.model.Product

class ProductController {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("products")
    private val TAG = "ProductController"

    fun getProducts(callback: (List<Product>) -> Unit) {
        Log.d(TAG, "Fetching products from Firebase...")

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val products = mutableListOf<Product>()
                Log.d(TAG, "DataSnapshot received: ${snapshot.childrenCount} items found.")

                for (productSnapshot in snapshot.children) {
                    val product = productSnapshot.getValue(Product::class.java)
                    if (product != null) {
                        Log.d(TAG, "Product loaded: ${product.name} (${product.id})")
                        products.add(product)
                    } else {
                        Log.w(TAG, "Failed to parse product: ${productSnapshot.key}")
                    }
                }

                callback(products)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, "Error fetching data from Firebase: ${error.message}")
                callback(emptyList())
            }
        })
    }
}
