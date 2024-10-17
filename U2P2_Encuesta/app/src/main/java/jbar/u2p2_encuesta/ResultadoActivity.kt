package jbar.u2p2_encuesta

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        // Referencias a los TextView
        val textViewCine = findViewById<TextView>(R.id.textViewCine)
        val textViewVerano = findViewById<TextView>(R.id.textViewVerano)
        val textViewLibros = findViewById<TextView>(R.id.textViewLibros)
        val textViewHobby = findViewById<TextView>(R.id.textViewHobby)
        val textViewMascota = findViewById<TextView>(R.id.textViewMascota)

        // Recibir los datos del Intent
        val cine = intent.getStringExtra("cine")
        val verano = intent.getStringExtra("verano")
        val libros = intent.getBooleanExtra("libros", false)
        val mascota = intent.getStringExtra("Mascota")
        val hobby = intent.getStringExtra("hobby")

        // Mostrar los resultados en los TextView
        textViewCine.text = "¿Te gusta el cine? $cine"
        textViewVerano.text = "¿Prefieres viajar en verano? $verano"
        textViewLibros.text = "¿Te gusta leer libros? ${if (libros) "Sí" else "No"}"
        textViewMascota.text = "¿Tienes mascota? $mascota"
        textViewHobby.text = "Hobby seleccionado: $hobby"
    }
}
