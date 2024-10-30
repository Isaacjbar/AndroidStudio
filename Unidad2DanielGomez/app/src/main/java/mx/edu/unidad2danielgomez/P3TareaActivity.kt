package mx.edu.unidad2danielgomez

import android.app.*
import android.os.*
import android.renderscript.RenderScript.Priority
import android.widget.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.sql.Date
import java.sql.Time


//Definicion de la clase P3TareaActivity que extiende AppComparativity,
//lo que significa que es una actividad de Android
class P3TareaActivity : AppCompatActivity() {

    //Declaracion de las variables para los componenetes de la interfaz.
    //Se usa "lateint" pára inicializar mas tarde.

    private lateinit var progressBar: ProgressBar
    private lateinit var seekBar: SeekBar
    private lateinit var ratingBar: RatingBar
    private lateinit var textViewPriority: TextView
    private lateinit var textViewDate: TextView
    private lateinit var textViewTime: TextView

    //variables para almacenar la fecha y la hora seleccionadas
    private var selectedDate = ""
    private var selectedTime = ""

    //metodo "onCreate" que se ejecuta cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        //Llama al metodo del padre para inicializar la actividad
        super.onCreate(savedInstanceState)
        //asocia la interfaz graficca con el archivo XML "activity_p3_tarea"
        setContentView(R.layout.activity_p3_tarea)

        //inicializacion de los elementos de la interface a travez de sus IDs

        progressBar = findViewById(R.id.progressBar)
        seekBar = findViewById(R.id.seekBar)
        ratingBar = findViewById(R.id.ratingBar)
        textViewPriority = findViewById(R.id.textViewPriority)
        textViewDate = findViewById(R.id.textViewDate)
        textViewTime = findViewById(R.id.textViewTime)

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonDatePicker = findViewById<Button>(R.id.buttonDatePiker)
        val buttonTimePicker = findViewById<Button>(R.id.buttonTimePicker)

        //inicialmente, el ProgressBar se oculta para que no sea visible
        //hasta que se inicie la accion de guardar

        progressBar.visibility = View.INVISIBLE

        //configuracion del "SeekBar" para ajustar la prioridad
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            //metodo que se ejecuta cuando cambia el progreso del "SeekBar"
            override fun onProgressChanged(SeekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //Atualiza al "TextView" para mostrar el valor de la prioridad seleccionadaq
                textViewPriority.text = "Propiedad: $progress"
            }

            //Metodo vacios para cuando de empieza y se detiene el movimiento del "SeekBar"
            override fun onStartTrackingTouch(SeekBar: SeekBar?) {}
            override fun onStopTrackingTouch(SeekBar: SeekBar?) {}
        })

        //Configuracion del boton para habrir el DatePickerDialog
        buttonDatePicker.setOnClickListener {
            //Muestra un dialogo para seleccionar una fecha
            //Al seleccionar la fecha, actualiza la variable y el "TexTView"
            val datePickerDialog = DatePickerDialog(this,{_,year,month,dayOfMonth ->
                selectedDate = "$dayOfMonth/${month + 1}/$year"
                textViewDate.text = "Fecha: $selectedDate"
            }, 2024,0,1)//Se inicia el dialogo con la fecha: 1 de enero de 2024
            datePickerDialog.show()
        }

        //Configuracion del boton para habrir el buttonTimePicker
        buttonTimePicker.setOnClickListener {
            //Muestra un dialogo para seleccionar una hora
            //Al seleccionar la hora, actualiza la variable y el "TexTView"
            val timePickerDialog = TimePickerDialog(this,{_,hourOfDay,minute ->
                selectedTime = "$hourOfDay:$minute"
                textViewTime.text = "Hora: $selectedTime"
            }, 12,0,true)//Se inicia el dialogo con la hora: 12:00 (en formato de 24 horas)
            timePickerDialog.show()
        }

        //Configuracion del boton para guardar tarea
        buttonSave.setOnClickListener {
            //Muestra el "ProbressBar" como visible para indicar que se resta realizando una accion
            progressBar.visibility = View.VISIBLE
            //Usa un "Handler" para simular un proceso de guardado que dura 3 segundos
            Handler(Looper.getMainLooper()).postDelayed({
                //Despues de los 3 segundos, ocultara el "ProgressBar" y muestra un mensaje de confirmacion
                progressBar.visibility = View.INVISIBLE
                Toast.makeText(this,"Tarea guardada", Toast.LENGTH_SHORT).show()
            },3000)// 3000 milisegundos (3 segundos)
        }

        //Configuraion del "RatingBar" para mostrar un mensaje cuando cambia la calificacion
        ratingBar.setOnRatingBarChangeListener{_, rating, _ ->
            //Muestra un mensaje con la calificacion seleccionada
            Toast.makeText(this, "Calificacion: $rating estrellas", Toast.LENGTH_SHORT).show()
        }
    }
}