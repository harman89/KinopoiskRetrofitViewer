package com.example.kinopoisk.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentFilmListBinding
import com.example.kinopoisk.databinding.FragmentRegisterBinding
import com.example.kinopoisk.viewModel.MainViewModel

class FilmListFragment : Fragment() {
    private lateinit var buttonSubmit : Button
    private lateinit var buttonBack : Button
    private lateinit var filmAdapter : FilmRecyclerAdapter
    private lateinit var binding: FragmentFilmListBinding
    private val viewModel by activityViewModels<MainViewModel>()
    companion object {
        fun newInstance() = FilmListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmListBinding.inflate(inflater)
        filmAdapter = FilmRecyclerAdapter()
        binding.recyclerFilmList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerFilmList.adapter = filmAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopFilms(1,"TOP_250_BEST_FILMS")
        viewModel.listFilms.observe(viewLifecycleOwner){
            response->response.body()?.let{
                filmAdapter.films = it.films
        }
        }
    }
    private fun goToFragment(fragment: Fragment) {
        viewModel.nextFragment(this,fragment)
    }
}