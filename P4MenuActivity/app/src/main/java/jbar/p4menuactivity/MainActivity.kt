package jbar.p4menuactivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.ActionMode
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //Infla el menu contextual desde los recursos
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.action_edit -> {
                Toast.makeText(this, "Edit selected", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_delete -> {
                Toast.makeText(this, "Delete selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "search selected", Toast.LENGTH_SHORT).show()
                true
            }

            R.id.action_settings -> {
                Toast.makeText(this, "settings selected", Toast.LENGTH_SHORT).show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    // Callback para el modo de acción
    val actionModeCallback = object: ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            mode.menuInflater.inflate(
                R.menu.menu_contextual_action_mode,
                menu
            ) // Infla el menú para el modo de acción
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean =
            false // Preparar el modo de acción (no hace nada en este caso)

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_delete -> {
                    // Eliminar los elementos seleccionados
                    Toast.makeText(this@MainActivity, "Items deleted", Toast.LENGTH_SHORT)
                        .show() // Mensaje para eliminación
                    mode.finish() // Finaliza el modo de acción
                    true
                }
                else -> false //Maneja otras selecciones
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            //Limpiar la seleccion (opcional)
        }
        // Clase de datos para representar un elemento
        data class MyItem(
            val name: String // Propiedad para el nombre del elemento
        )

        // Adaptador para el RecyclerView
        class MyAdapter(
            private val itemList: List<MyItem>,
            private val activity: P4MenuActivity
        ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

            // ViewHolder para representar cada elemento en el RecyclerView
            inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                private val textView: TextView =
                    itemView.findViewById(R.id.text_item) // Referencia al TextView
                private val optionsButton: ImageButton =
                    itemView.findViewById(R.id.options_button) // Referencia al botón de opciones
            }

            // Método para enlazar un elemento con el ViewHolder
            fun bind(item: MyItem) {
                textView.text = item.name // Asigna el nombre al TextView
            }
        }
    }
}