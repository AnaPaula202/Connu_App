package com.example.connu.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class MainPageFragment : Fragment() {
    private lateinit var adapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_main_page, container, false)

        val lista: RecyclerView = rootView.findViewById(R.id.posts)

        adapter = PostAdapter(this)

        lista.adapter = adapter

        val linearLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)

        lista.layoutManager = linearLayoutManager

        // Ahora que el adaptador está asignado, configura el LayoutManager

        return rootView

    }

    fun consultarLista(){
        val requestQueue = Volley.newRequestQueue(requireActivity())
        val url : String = "http://192.168.1.72/connu/listarPosts.php"

        val request : JsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener { response ->
                procesarLista(response)
            },
            Response.ErrorListener { error ->
                //Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
            }
        )

        requestQueue.add(request)
    }

    private fun procesarLista(response: JSONObject?) {
        if(response != null && response.getBoolean("exito")){
            val lista : JSONArray = response.getJSONArray("lista")
            val datos : ArrayList<Post> = ArrayList()

            for(i in 0 .. lista.length() - 1){
                val registro : JSONObject = lista.getJSONObject(i)

                val post = Post()

                post.idpost = registro.getInt("idpublicacion")
                post.content = registro.getString("contenido")
                post.img = registro.getString("img1")
                post.user = registro.getString("nombre")
                post.usermail = registro.getString("correo")
                post.ptype = registro.getString("tipo_idtipo")
                post.likes = registro.getString("likes")

                datos.add(post)
            }

            adapter.llenar(datos)
        }
    }



}