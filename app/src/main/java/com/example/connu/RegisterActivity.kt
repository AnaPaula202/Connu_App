package com.example.connu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    private lateinit var etRName: EditText
    private lateinit var etRMail: EditText
    private lateinit var etRPass: EditText
    private lateinit var etRRepeatPass: EditText
    private lateinit var sRGender: Spinner
    private lateinit var bConfirmRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvGoback : TextView = findViewById(R.id.tvGoback)
        val ivGoback : ImageView = findViewById(R.id.ivGoback)

        tvGoback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        ivGoback.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Inicializa tus elementos
        etRName = findViewById(R.id.etRName)
        etRMail = findViewById(R.id.etRMail)
        etRPass = findViewById(R.id.etRPass)
        etRRepeatPass = findViewById(R.id.etRRepeatPass)
        sRGender = findViewById(R.id.sRGender)
        bConfirmRegister = findViewById(R.id.bConfirmRegister)

        // Configura el Spinner con datos ficticios
        val genderAdapter = ArrayAdapter.createFromResource(this, R.array.genders, android.R.layout.simple_spinner_item)
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sRGender.adapter = genderAdapter

        // Configura el clic del botón de registro
        bConfirmRegister.setOnClickListener {
            val name = etRName.text.toString()
            val mail = etRMail.text.toString()
            val password = etRPass.text.toString()
            val repeatPassword = etRRepeatPass.text.toString()
            val gender = sRGender.selectedItem.toString()

            // Realiza la solicitud al servidor
            enviarDatosAlServidor(name, mail, password, repeatPassword, gender)
        }
    }

    private fun enviarDatosAlServidor(
        name: String,
        mail: String,
        password: String,
        repeatPassword: String,
        gender: String
    ) {
        val url = "http://connu/registrarUsuario.php"  // Reemplaza con la URL correcta

        val requestQueue = Volley.newRequestQueue(this)
        val mapa = mutableMapOf<String, Any?>()

        // Agrega tus parámetros al mapa
        mapa["nombre"] = name
        mapa["correo"] = mail
        mapa["contrasena"] = password
        mapa["repetir_contrasena"] = repeatPassword
        mapa["sexo"] = gender

        val parametros: JSONObject = JSONObject(mapa)

        // Crea la solicitud JSON para enviar al servidor
        val request: JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            parametros,
            Response.Listener { response ->
                // Maneja la respuesta del servidor aquí
                Log.d("RegisterActivity", "Respuesta del servidor: $response")
                // Puedes mostrar un mensaje de éxito o error según la respuesta del servidor
            },
            Response.ErrorListener { error ->
                // Maneja el error de la solicitud al servidor aquí
                Log.e("RegisterActivity", "Error en la solicitud al servidor: ${error.message}")
                // Puedes mostrar un mensaje de error al usuario si es necesario
            }
        )

        // Agrega la solicitud a la cola de solicitudes
        requestQueue.add(request)
    }
}