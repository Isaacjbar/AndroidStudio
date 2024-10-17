package jbar.u2p2_encuesta

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class EncuestaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encuesta)

        // Referencias a los elementos de la UI
        val edTextCine = findViewById<EditText>(R.id.textCine)
        val edTextViajar = findViewById<EditText>(R.id.textViajar)
        val checkboxLibrosS = findViewById<CheckBox>(R.id.checkbox_librosS)
        val checkboxLibrosN = findViewById<CheckBox>(R.id.checkbox_librosN)
        val spinnerHobbies = findViewById<Spinner>(R.id.spinner_hobbies)
        val edTextMascota = findViewById<EditText>(R.id.textMascota)
        val btnEnviar = findViewById<Button>(R.id.btn_enviar)


        // Lista de valores para el Spinner
        val listaOpciones: List<String> = mutableListOf("Deportes", "Música", "Videojuegos")
        // Crear un ArrayAdapter usando el contexto, el layout por defecto para spinner, y la lista
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaOpciones)
        // Especificar el layout que se usará cuando se desplieguen las opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Asignar el adaptador al Spinner
        spinnerHobbies.setAdapter(adapter)

        // Listener para el botón "Enviar respuestas"
        btnEnviar.setOnClickListener {
            // Obtener las respuestas del usuario
            val textCine = edTextCine.text.toString()
            val textViajar = edTextViajar.text.toString()
            var checkL = true
            if(checkboxLibrosS.isChecked()){
                checkL = true
            } else {
                checkL = false
            }
            val textMascota = edTextMascota.text.toString()
            val hobby = spinnerHobbies.selectedItem.toString()


            // Crear un Intent para enviar los datos a ResultadoActivity
            val intent = Intent(this, ResultadoActivity::class.java)
            intent.putExtra("cine", textCine)
            intent.putExtra("verano", textViajar)
            intent.putExtra("libros", checkL)
            intent.putExtra("Mascota", textMascota)
            intent.putExtra("hobby", hobby)

            // Iniciar el nuevo Activity
            startActivity(intent)
        }
    }
}
