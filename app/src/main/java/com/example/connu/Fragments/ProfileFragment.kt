package com.example.connu.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.connu.Post
import com.example.connu.PostAdapter
import com.example.connu.R
import org.json.JSONArray
import org.json.JSONObject

class ProfileFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        consultarUser()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    fun consultarUser() {
        val sharedPreferences = requireContext().getSharedPreferences("mi_pref", Context.MODE_PRIVATE)

        // Obtener el idUsuario del SharedPreferences
        val idUsuario = sharedPreferences.getInt("idUsuario", -1)

        val requestQueue = Volley.newRequestQueue(requireActivity())

        // Asegúrate de ajustar la URL según tu entorno y ruta del script
        val url: String = "http://192.168.1.67/connu/visualizarUsuario.php?idUsuario=$idUsuario"

        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                procesarUser(response)
            },
            Response.ErrorListener { error ->
                //Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(request)
    }


    private fun procesarUser(response: JSONObject?) {
        if (response != null && response.getBoolean("exito")) {
            // Obtener datos del usuario desde la respuesta JSON
            val nombreUsuario = response.getString("nombre")
            val correoUsuario = response.getString("correo")


            val etNombre: EditText = requireView().findViewById(R.id.etNameProfile)
            val etCorreo: EditText = requireView().findViewById(R.id.etMailProfile)

            etNombre.setText(nombreUsuario)
            etCorreo.setText(correoUsuario)

        } else {

        }
    }



}