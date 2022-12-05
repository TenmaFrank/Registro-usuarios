package com.tenmafrank.registrousuarios.view.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tenmafrank.registrousuarios.R
import com.tenmafrank.registrousuarios.databinding.FragmentRegisterBinding
import com.tenmafrank.registrousuarios.utils.Constants
import com.tenmafrank.registrousuarios.utils.InputValidation
import com.tenmafrank.registrousuarios.utils.Toster
import com.tenmafrank.registrousuarios.utils.sharedpreferences.UserApplication.Companion.preferences
import com.tenmafrank.registrousuarios.view.activity.UsersActivity
import com.tenmafrank.registrousuarios.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private val toster = Toster()
    private val inputValidation = InputValidation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)

        with(binding){
            if (preferences.getEmailUser().isNotEmpty()){
                emailInputEditText.setText(preferences.getEmailUser())
            }

            if(preferences.getTemporalPassword().isNotEmpty()){
                passInputEditText.setText(preferences.getTemporalPassword())
                passConfirmInputEditText.setText(preferences.getTemporalPassword())
            }

            signUpButton.setOnClickListener {
                val userName = userNameInputEditText.text.toString().trim()
                when(inputValidation.validate(userName)){
                    1 -> userNameInput.error = getString(R.string.empty_input_label)
                    2 -> userNameInput.error = getString(R.string.emoji_on_input)
                    else -> userNameInput.error = null
                }
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
                val passConfirm = passConfirmInputEditText.text.toString().trim()
                when(inputValidation.validate(passConfirm)){
                    1 -> passConfirmInput.error = getString(R.string.empty_input_label)
                    2 -> passConfirmInput.error = getString(R.string.emoji_on_input)
                    else -> passConfirmInput.error = null
                }

                if (userNameInput.error.isNullOrEmpty() && emailInput.error.isNullOrEmpty() &&
                        passInput.error.isNullOrEmpty()){
                    if(pass != passConfirm){
                        passInput.error = getString(R.string.pass_dont_match)
                        passConfirmInput.error = getString(R.string.pass_dont_match)
                    }
                    else{
                        passInput.error = null
                        passConfirmInput.error = null
                        preferences.saveNameUser(userName)
                        preferences.saveEmailUser(email)
                        preferences.saveTemporalPassword(pass)
                        viewLifecycleOwner.lifecycleScope.launch{
                            val id = viewModel.doRegister(userName, email, pass)
                            if (id.equals(Constants.LOGIN_ERROR_LABEL)){
                                toster.makeAToast(activity as Context,Constants.CANT_REGISTER)
                            }
                            else{
                                toster.makeAToast(activity as Context,Constants.REGISTED)
                                val intent = Intent(activity, UsersActivity::class.java)
                                val context = view?.context
                                context?.startActivity(intent)
                            }
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