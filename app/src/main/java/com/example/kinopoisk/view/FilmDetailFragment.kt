package com.example.kinopoisk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentFilmDetailBinding
import com.example.kinopoisk.databinding.FragmentRegisterBinding
import com.example.kinopoisk.model.retrofit.data.Film
import com.example.kinopoisk.viewModel.MainViewModel
import com.squareup.picasso.Picasso

class FilmDetailFragment : Fragment() {
    private lateinit var binding: FragmentFilmDetailBinding
    private lateinit var film : Film
    private val viewModel by activityViewModels<MainViewModel>()
    companion object {
        fun newInstance() = FilmDetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ratingBar.numStars = 10
        var filmId : Long? = 0
        filmId = viewModel.chosenFilmId.value
        if (filmId != null) {
            viewModel.getFilm(filmId)
        }
        viewModel.film.observe(viewLifecycleOwner){
            resp->
            resp.body()?.let {
                binding.textFilmName.text = it.nameRu
                binding.textFilmDescription.text = it.description
                binding.ratingBar.rating = it.ratingKinopoisk.toFloat()
                Picasso.get().load(it.posterUrl).into(binding.imageFilm)
            }
        }
    }
    private fun goToFragment(fragment: Fragment) {
        viewModel.nextFragment(this,fragment)
    }
}