package mx.edu.unidad2danielgomez

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DestinoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_destino)


        val tvMessage = findViewById<TextView>(R.id.tvMessage)

        val email = intent.getStringExtra("Email")
        val password = intent.getStringExtra("password")

        if (email == "admin@gmail.com" && password == "1234"){
            tvMessage.text = "Bienvenido, $email!"
        } else {
            tvMessage.text = "Error: el usuario o contraseña son incorrectos"
            Toast.makeText(this, "Intentalo de nuevo", Toast.LENGTH_SHORT).show()
        }
    }
}