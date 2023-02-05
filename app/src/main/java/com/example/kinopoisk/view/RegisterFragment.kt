package com.example.kinopoisk.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.kinopoisk.MainActivity
import com.example.kinopoisk.R
import com.example.kinopoisk.databinding.FragmentLoginBinding
import com.example.kinopoisk.databinding.FragmentRegisterBinding
import com.example.kinopoisk.viewModel.MainViewModel

class RegisterFragment : Fragment() {
    private lateinit var buttonSubmit : Button
    private lateinit var buttonBack : Button
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var textEmail : EditText
    private lateinit var textPassword : EditText
    private lateinit var textPasswordConfirm : EditText
    private lateinit var valTextEmail : String
    private lateinit var valTextPassword : String
    private lateinit var valTextPasswordConfirm : String
    private val viewModel by activityViewModels<MainViewModel>()
    companion object {
        fun newInstance() = RegisterFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonSubmit = binding.buttonSubmit
        buttonBack = binding.buttonRBack
        textEmail = binding.editTextTextEmailAddress2
        textPassword = binding.editTextTextPassword3
        textPasswordConfirm = binding.editTextTextPassword2
        buttonSubmit.setOnClickListener{
            valTextEmail = textEmail.text.toString()
            valTextPassword = textPassword.text.toString()
            valTextPasswordConfirm = textPasswordConfirm.text.toString()
            if(valTextEmail.isEmpty() && valTextPassword.isEmpty() && valTextPasswordConfirm.isEmpty())
            {
                Toast.makeText(requireContext(),"Enter all values",Toast.LENGTH_SHORT).show()
            }
            else {
                if (valTextPassword == valTextPasswordConfirm) {
                    viewModel.getUser(valTextEmail, valTextPassword).observe(activity as MainActivity, Observer {
                        if(it == null)
                        {
                            viewModel.insertUser(valTextEmail, valTextPassword)
                            goToFragment(LoginFragment.newInstance())
                        }
                        else{
                            Toast.makeText(requireContext(), "User already exists", Toast.LENGTH_SHORT)
                                .show()
                        }
                    })
                } else {
                    Toast.makeText(requireContext(), "Passwords must match!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        buttonBack.setOnClickListener{
            goToFragment(EnterFragment.newInstance())
        }
    }
    private fun goToFragment(fragment: Fragment) {
        viewModel.nextFragment(this,fragment)
    }
}