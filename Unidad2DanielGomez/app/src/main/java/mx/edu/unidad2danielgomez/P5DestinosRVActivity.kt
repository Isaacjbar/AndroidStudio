package mx.edu.unidad2danielgomez

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class P5DestinosRVActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var destinoAdapter: DestinoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p5_destinos_rvactivity)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val destinos = listOf(
            DestinoTuristico(
                nombre = "Alpes Suizos",
                descripcion = "Chido",
                imagenResId = R.drawable.alpes_suizos
            ),
            DestinoTuristico(
                nombre = "Catacumbas",
                descripcion = "Chidoooo",
                imagenResId = R.drawable.catacombs
            ),
            DestinoTuristico(
                nombre = "Torre Eiffel",
                descripcion = "aaaa.",
                imagenResId = R.drawable.torre_eiffel
            )
        )
        destinoAdapter = DestinoAdapter(this, destinos)
        recyclerView.adapter = destinoAdapter
    }
}
