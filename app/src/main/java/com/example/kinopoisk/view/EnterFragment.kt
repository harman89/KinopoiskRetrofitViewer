package com.example.kinopoisk.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentEnterBinding
import com.example.kinopoisk.viewModel.MainViewModel

class EnterFragment : Fragment() {
    private lateinit var buttonLogin : Button
    private lateinit var buttonRegister : Button
    private lateinit var binding: FragmentEnterBinding
    private val viewModel by activityViewModels<MainViewModel>()
    companion object {
        fun newInstance() = EnterFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEnterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loginLoggedUser().observe(activity as MainActivity){
            if(it!=null)
            {
                goToFragment(FilmListFragment.newInstance())
            }
        }
        buttonLogin = binding.buttonLogin
        buttonRegister = binding.buttonRegister
        buttonRegister.setOnClickListener{
            goToFragment(RegisterFragment.newInstance())
        }
        buttonLogin.setOnClickListener{
            goToFragment(LoginFragment.newInstance())
        }
    }
    private fun goToFragment(fragment: Fragment) {
        viewModel.nextFragment(this,fragment)
    }
}