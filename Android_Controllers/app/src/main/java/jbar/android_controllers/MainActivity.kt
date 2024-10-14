package jbar.android_controllers

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de las variables para cada control
    private lateinit var datePicker: DatePicker
    private lateinit var timePicker: TimePicker
    private lateinit var seekBar: SeekBar
    private lateinit var progressBar: ProgressBar
    private lateinit var ratingBar: RatingBar
    private lateinit var btnIniciarTarea: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de los controles definidos en el XML
        datePicker = findViewById(R.id.datePicker)  // Control para seleccionar la fecha
        timePicker = findViewById(R.id.timePicker)  // Control para seleccionar la hora
        seekBar = findViewById(R.id.seekBar)        // Control para seleccionar el nivel de prioridad
        progressBar = findViewById(R.id.progressBar) // Barra de progreso de la tarea
        ratingBar = findViewById(R.id.ratingBar)    // Barra para calificar la tarea
        btnIniciarTarea = findViewById(R.id.btnIniciarTarea) // Botón para iniciar la tarea

        // Configurar el formato de 24 horas para el TimePicker
        timePicker.setIs24HourView(true)  // Muestra el formato de 24 horas

        // Listener del SeekBar: Se usa para mostrar el valor de prioridad cuando el usuario lo cambia
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Muestra el valor de la prioridad seleccionada mediante un Toast
                Toast.makeText(this@MainActivity, "Prioridad: $progress", Toast.LENGTH_SHORT).show()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // No se requiere ninguna acción al comenzar a deslizar
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // No se requiere ninguna acción al terminar de deslizar
            }
        })

        // Configuración del botón para iniciar la tarea
        btnIniciarTarea.setOnClickListener {
            // Obtener la fecha seleccionada por el usuario
            val day = datePicker.dayOfMonth   // Día seleccionado
            val month = datePicker.month + 1  // Mes seleccionado (el índice comienza en 0, por lo que sumamos 1)
            val year = datePicker.year        // Año seleccionado

            // Obtener la hora seleccionada dependiendo de la versión de la API
            val hour: Int
            val minute: Int

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                // Para versiones API 23 o superiores, se usan los métodos getHour() y getMinute()
                hour = timePicker.hour
                minute = timePicker.minute
            } else {
                // Para versiones anteriores a API 23, se usan getCurrentHour() y getCurrentMinute()
                hour = timePicker.currentHour
                minute = timePicker.currentMinute
            }

            // Mostrar la fecha y hora seleccionada usando un Toast
            Toast.makeText(
                this,
                "Tarea programada para: $day/$month/$year a las $hour:$minute",
                Toast.LENGTH_SHORT
            ).show()

            // Simular el progreso de la tarea: Establecemos el progreso en 50%
            progressBar.progress = 50  // Puedes cambiarlo a 100% para indicar que la tarea está completada

            // Obtener la calificación de la tarea mediante el RatingBar
            val rating = ratingBar.rating  // La calificación es un valor flotante (ejemplo: 4.5)
            Toast.makeText(this, "Calificación de la tarea: $rating estrellas", Toast.LENGTH_SHORT).show()
        }
    }
}
