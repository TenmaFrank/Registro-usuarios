package com.tenmafrank.registrousuarios.view.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.tenmafrank.registrousuarios.R
import com.tenmafrank.registrousuarios.databinding.FragmentLoginBinding
import com.tenmafrank.registrousuarios.utils.Constants
import com.tenmafrank.registrousuarios.utils.InputValidation
import com.tenmafrank.registrousuarios.utils.Toster
import com.tenmafrank.registrousuarios.utils.sharedpreferences.UserApplication.Companion.preferences
import com.tenmafrank.registrousuarios.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val toster = Toster()
    private val inputValidation = InputValidation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)

        with(binding){
            signUpButton.setOnClickListener {
                val email = emailInputEditText.text.toString().trim()
                when(inputValidation.validate(email)){
                    2 -> emailInput.error = getString(R.string.emoji_on_input)
                    else -> emailInput.error = null
                }
                val pass = passInputEditText.text.toString().trim()
                when(inputValidation.validate(pass)){
                    2 -> passInput.error = getString(R.string.emoji_on_input)
                    else -> passInput.error = null
                }
                if (passInput.error.isNullOrEmpty() && emailInput.error.isNullOrEmpty()){
                    preferences.saveEmailUser(email)
                    preferences.saveTemporalPassword(pass)
                    view?.findNavController()?.navigate(R.id.action_loginFragment_to_registerFragment)
                }
                else{
                    toster.makeAToast(activity as Context, "Verifique los campos")
                }
            }
            logInButton.setOnClickListener {
                val email = emailInputEditText.text.toString().trim()
                when(inputValidation.validate(email)){
                    1 -> emailInput.error = getString(R.string.empty_input_label)
                    2 -> emailInput.error = getString(R.string.emoji_on_input)
                    else -> emailInput.error = null
                }
                val pass = passInputEditText.text.toString().trim()
                when(inputValidation.validate(pass)){
                    1 -> passInput.error = getString(R.string.empty_input_label)
                    2 -> passInput.error = getString(R.string.emoji_on_input)
                    else -> passInput.error = null
                }

                if (passInput.error.isNullOrEmpty() && emailInput.error.isNullOrEmpty()){
                    viewLifecycleOwner.lifecycleScope.launch{
                        val token = viewModel.doLogin(email, pass)
                        if(token.equals(Constants.LOGIN_ERROR_LABEL)){
                            toster.makeAToast(activity as Context, "Usuario no Registrado")
                        }
                        else{
                            toster.makeAToast(activity as Context, "Exito...")
                        }
                    }
                }
                else{
                    toster.makeAToast(activity as Context, "Verifique los campos")
                }
            }
        }

        return binding.root
    }
}