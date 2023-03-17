package com.example.kinopoisk.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.databinding.FilmItemBinding
import com.example.kinopoisk.model.retrofit.data.FilmLite
import com.squareup.picasso.Picasso

class FilmRecyclerAdapter(var films : List<FilmLite> = emptyList(), private val onClick : (FilmLite) -> Unit) : RecyclerView.Adapter<FilmRecyclerAdapter.FilmViewHolder>() {

    class FilmViewHolder(binding : FilmItemBinding, val onClick: (FilmLite) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        private val imgPoster : ImageView = binding.imageViewFilmList
        private val txtTitle : TextView = binding.textFilmNameList
        private var currentFilm : FilmLite? = null

        init {
            binding.root.setOnClickListener{
                currentFilm?.let {
                    onClick(it)
                }
            }
        }

        fun bind(film: FilmLite) {
            currentFilm = film
            Picasso.get().load(film.posterUrl).into(imgPoster)
            txtTitle.text = film.nameRu
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView : LayoutInflater = LayoutInflater.from(parent.context)
        val binding = FilmItemBinding.inflate(itemView, parent, false)
        return FilmViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = films[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return films.size
    }

}