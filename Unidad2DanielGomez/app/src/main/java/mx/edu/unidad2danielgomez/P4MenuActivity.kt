package mx.edu.unidad2danielgomez

import android.content.Intent
import android.os.Bundle
import android.view.ActionMode
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class P4MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p4_menu)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        registerForContextMenu(recyclerView)

        val itemList = listOf(
            MyItem(name = "Item 1"),
            MyItem(name = "Item 2"),
            MyItem(name = "Item 3")
        )

        val adapter = MyAdapter(itemList, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            Toast.makeText(this, "FAB clicked", Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this, P3TareaActivity
            ::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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

    // Crea el menú de opciones en la parte superior
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // Infla el menú principal
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                Toast.makeText(this, "Search selected", Toast.LENGTH_SHORT).show() // Mensaje para búsqueda
                true
            }
            R.id.action_settings -> {
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT).show() // Mensaje para configuración
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // Callback para el modo de acción
    private val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode, menu: Menu): Boolean {
            mode.menuInflater.inflate(R.menu.menu_contextual_action_mode, menu) // Infla el menú para el modo de acción
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode, menu: Menu): Boolean = false

        override fun onActionItemClicked(mode: ActionMode, item: MenuItem): Boolean {
            return when (item.itemId) {
                R.id.action_delete -> {
                    Toast.makeText(this@P4MenuActivity, "Items deleted", Toast.LENGTH_SHORT).show() // Mensaje para eliminación
                    mode.finish() // Finaliza el modo de acción
                    true
                }
                else -> false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode) {
            // Código para realizar tareas cuando el modo de acción finaliza
        }
    }

    // Modelo de datos
    data class MyItem(val name: String)

    // Adaptador para el RecyclerView
    class MyAdapter(
        private val itemList: List<MyItem>,
        private val activity: P4MenuActivity
    ) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {
            private val textView: TextView = itemView.findViewById(R.id.text_item)
            private val optionButton: ImageButton = itemView.findViewById(R.id.options_button)

            fun bind(item: MyItem) {
                textView.text = item.name

                optionButton.setOnClickListener { view ->
                    showPopupMenu(view)
                }

                itemView.setOnClickListener {
                    activity.startActionMode(activity.actionModeCallback)
                }
            }

            private fun showPopupMenu(view: View) {
                val popup = PopupMenu(view.context, view)
                popup.inflate(R.menu.menu_papup)
                popup.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.action_view -> {
                            Toast.makeText(view.context, "View selected", Toast.LENGTH_SHORT).show()
                            true
                        }
                        R.id.action_share -> {
                            Toast.makeText(view.context, "Share selected", Toast.LENGTH_SHORT).show()
                            true
                        }
                        else -> false
                    }
                }
                popup.show()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = itemList[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = itemList.size
    }
}
