package com.example.connu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.connu.Fragments.MainPageFragment

class PostAdapter : RecyclerView.Adapter<PostViewHolder> {
    private lateinit var datos: ArrayList<Post>

    constructor(context: MainPageFragment) {
        datos = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.post_fila, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return datos.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = datos.get(position)

        if(post.img.isEmpty()) {
            holder.tvCuerpoPost.text = post.content
        } else {
            //Picasso.get().load(post.img).into(holder.ivImagenPost)
            holder.ivImagenPost.visibility = View.VISIBLE
            holder.tvCuerpoPost.visibility = View.GONE
        }
    }

    fun llenar(datos: ArrayList<Post>) {
        this.datos = datos
        this.notifyDataSetChanged()
    }
}