package com.example.kinopoisk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentEnterBinding
import com.example.kinopoisk.databinding.FragmentLoginBinding
import com.example.kinopoisk.model.db.UserClass
import com.example.kinopoisk.viewModel.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var buttonLogin : Button
    private lateinit var buttonRegister : Button
    private lateinit var buttonBack : Button
    private lateinit var textEmail : EditText
    private lateinit var textPassword : EditText
    private lateinit var rememberMe : Switch
    private lateinit var valTextEmail : String
    private lateinit var valTextPassword : String
    private lateinit var binding: FragmentLoginBinding
    private val viewModel by activityViewModels<MainViewModel>()
    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonLogin = binding.buttonLLogin
        buttonRegister = binding.buttonLRegistration
        buttonBack = binding.buttonLBack
        textEmail = binding.editTextTextEmailAddress
        textPassword = binding.editTextTextPassword
        rememberMe = binding.switch1
        buttonRegister.setOnClickListener{
            goToFragment(RegisterFragment.newInstance())
        }
        buttonLogin.setOnClickListener{
            valTextEmail = textEmail.text.toString()
            valTextPassword = textPassword.text.toString()

            viewModel.getUser(valTextEmail,valTextPassword).observe(activity as MainActivity, Observer {
                if(it!=null){
                    if(rememberMe.isChecked) {
                        it.isLogged = true
                        viewModel.updateUser(it)
                    }
                    goToFragment(FilmListFragment.newInstance())
                }
                else{
                    Toast.makeText(requireContext(),"Wrong Email or Password. Try again", Toast.LENGTH_SHORT).show()
                }
            })
        }
        buttonBack.setOnClickListener{
            goToFragment(EnterFragment.newInstance())
        }
    }
    private fun goToFragment(fragment: Fragment) {
        viewModel.nextFragment(this,fragment)
    }
}