package com.example.kinopoisk.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.databinding.FilmItemBinding
import com.example.kinopoisk.model.retrofit.data.FilmLite
import com.squareup.picasso.Picasso

class FilmRecyclerAdapter(var films : List<FilmLite> = emptyList()) : RecyclerView.Adapter<FilmRecyclerAdapter.FilmViewHolder>() {

    class FilmViewHolder(binding : FilmItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgPoster : ImageView = binding.imageViewFilmList
        val txtTitle : TextView = binding.textFilmNameList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(itemView, parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        Picasso.get().load(films[position].posterUrl).into(holder.imgPoster)
        holder.txtTitle.text = films[position].nameRu
    }

    override fun getItemCount(): Int {
        return films.size
    }

}