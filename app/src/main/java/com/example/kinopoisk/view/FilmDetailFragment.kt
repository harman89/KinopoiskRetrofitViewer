package com.example.kinopoisk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentFilmDetailBinding
import com.example.kinopoisk.databinding.FragmentRegisterBinding
import com.example.kinopoisk.viewModel.MainViewModel

class FilmDetailFragment : Fragment() {
    private lateinit var buttonSubmit : Button
    private lateinit var buttonBack : Button
    private lateinit var binding: FragmentFilmDetailBinding
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
//        buttonSubmit = binding.buttonSubmit
//        buttonBack = binding.buttonRBack
//        buttonSubmit.setOnClickListener{
//            goToFragment(LoginFragment.newInstance())
//        }
//        buttonBack.setOnClickListener{
//            goToFragment(EnterFragment.newInstance())
//        }
    }
    private fun goToFragment(fragment: Fragment) {
        viewModel.nextFragment(this,fragment)
    }
}