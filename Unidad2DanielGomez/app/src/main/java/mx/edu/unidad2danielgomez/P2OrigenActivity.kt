package mx.edu.unidad2danielgomez

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class P2OrigenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p2_origen)

        val etEmail= findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            val intent = Intent(this,DestinoActivity::class.java)
            intent.putExtra("Email",email)
            intent.putExtra("password",password)

            startActivity(intent)
        }
        btnCancel.setOnClickListener {
            finish()
        }
    }
}