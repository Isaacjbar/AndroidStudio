package mx.edu.unidad2danielgomez

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P1MensajesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p1_mensajes)

        //Referencia al boton
        val buttonShowDialog = findViewById<Button>(R.id.buttonShowDialog)

        //Listener para el boton
        buttonShowDialog.setOnClickListener {
            //AlertDialog: El dialogo se muestra cuando el usuario
            //tocar el boton "Mostrar Alerta". tiene dos opciones,
            //"Aceptar" y "Cancelar". Si selecciona "Aceptar", se muestra un Toast y registra un mensaje en el log
            //Crea el AlertDialog

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirmacion")
            builder.setMessage("¿Estas seguro de que quieres continuar?")
            builder.setPositiveButton("Aceptar") { dialog, which ->
                //Mostrar un Toast al confirmar
                //Toast: Se muestra cuando el usuario
                //hace click en "Aceptar" dentro del dialogo de alerta
                Toast.makeText(this,"Operacion confirmada", Toast.LENGTH_SHORT).show()
                Log.d("MainActivity","El usuario confirmo la operacion")
                Log.v("MainActivity","Mensaje muy detallado (Verbose)")
                Log.d("MainActivity","Mensaje de depuracion (Debug)")
                Log.i("MainActivity","Informacion general (Info)")
                Log.w("MainActivity","Advertencia de posible problema (Warning)")
                Log.e("MainActivity","Error Critivo (Error)")
                Log.wtf("MainActivity","Fallo algo que no deberia haber fallado (WTF)")
            }

            builder.setNegativeButton("Cancelar"){ dialog, which ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}