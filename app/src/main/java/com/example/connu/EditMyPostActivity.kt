package com.example.connu

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class EditMyPostActivity : AppCompatActivity() {

    lateinit var spCateg: Spinner
    lateinit var ncontent: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_post)

        // Inicializar vistas dentro del método onCreate
        spCateg = findViewById(R.id.spEditPType)
        ncontent = findViewById(R.id.etEditPContent)

        // Obtener el Intent que inició esta actividad
        val intent = intent

        // Verificar si el Intent tiene datos extras
        if (intent.hasExtra("postId")) {
            // Obtener el ID del post del Intent
            val postId = intent.getIntExtra("postId", -1)
            obtenerDetallesPost(postId)
        }

        val cancelButton: Button = findViewById(R.id.bCancelEditPost)
        cancelButton.setOnClickListener {
            finish()
        }

        val sharedPreferences =
            getSharedPreferences("mi_pref", Context.MODE_PRIVATE)
        val tipoUsuario = sharedPreferences.getString("tipoUsuario", "indefinido")

        val opciones: List<String> = when (tipoUsuario) {
            "General" -> listOf("Buscador de servicios")
            "Prestador" -> listOf("Buscador de servicios", "Proveedor de servicios")
            else -> emptyList()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spCateg.adapter = adapter

        val EditPostButton: Button = findViewById(R.id.bEditPost)
        EditPostButton.setOnClickListener {
            val seleccion = spCateg.selectedItem.toString()
            val opc = if (seleccion == "Proveedor de servicios") 2 else 1
            val content = ncontent.text.toString()

            if (content.isNotEmpty()) {
                val postId = intent.getIntExtra("postId", -1)
                editPost(opc, postId, content, "")
            } else {
                Toast.makeText(this, "El contenido no puede estar vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun obtenerDetallesPost(postId: Int) {
        val url = "http://192.168.68.133/connu/obtenerDetallesPost.php"

        val requestQueue = Volley.newRequestQueue(this)
        val mapa = mutableMapOf<String, Any?>()

        mapa.put("postId", postId)

        val parametros: JSONObject = JSONObject(mapa)

        val request: JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            parametros,
            Response.Listener { response ->
                if (response.getBoolean("exito")) {
                    val tipo = response.getInt("tipo")
                    val contenido = response.getString("contenido")
                    val img = response.getString("img")

                    spCateg.setSelection(tipo - 1)
                    ncontent.text = contenido
                } else {
                    Toast.makeText(this, "Error al obtener detalles del post", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Log.e("EditMyPostActivity", "Error en la solicitud HTTP: ${error.message}")
            }
        )

        requestQueue.add(request)
    }

    private fun editPost(
        opc: Int,
        idpost: Int,
        content: String,
        img: String
    ) {
        val url = "http://192.168.68.133/connu/editPost.php"

        val requestQueue = Volley.newRequestQueue(this)
        val mapa = mutableMapOf<String, Any?>()

        mapa.put("tipo", opc)
        mapa.put("id", idpost)
        mapa.put("contenido", content)
        mapa.put("img", img)

        val parametros: JSONObject = JSONObject(mapa)

        val request: JsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,
            url,
            parametros,
            Response.Listener { response ->
                if (response.getBoolean("exito")) {
                    finish()
                } else {
                    Toast.makeText(this, "Error en el servicio web", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Log.e("NewPostActivity", "Error en la solicitud HTTP: ${error.message}")
            }
        )

        requestQueue.add(request)
    }
}
