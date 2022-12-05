package com.tenmafrank.registrousuarios.view.fragment

import android.content.Context
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
import com.tenmafrank.registrousuarios.databinding.FragmentCreateBinding
import com.tenmafrank.registrousuarios.model.dto.CreateUpdateResponse
import com.tenmafrank.registrousuarios.utils.Toster
import com.tenmafrank.registrousuarios.utils.sharedpreferences.UserApplication
import com.tenmafrank.registrousuarios.viewmodel.CreateViewModel
import kotlinx.coroutines.launch

class CreateFragment : Fragment() {

    private lateinit var binding: FragmentCreateBinding
    private val viewModel: CreateViewModel by viewModels()
    private val toster = Toster()

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
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create, container, false)
        with(binding){

            if(UserApplication.preferences.getNameUser().isEmpty()){
                appbarText.setText(UserApplication.preferences.getNameUser())
            }
            else{
                appbarText.setText(UserApplication.preferences.getEmailUser())
            }


            saveButton.setOnClickListener {
                val firstName = firstNameInputEditText.text.toString().trim()
                val lastName = lastInputEditText.text.toString().trim()
                val job = jobInputEditText.text.toString().trim()
                val email = jobInputEditText.text.toString().trim()
                viewLifecycleOwner.lifecycleScope.launch{
                    var createRes = CreateUpdateResponse("","","","","")
                        try {
                        createRes = viewModel.doCreate(firstName, job)!!
                    }
                    finally {
                        toster.makeAToast(activity as Context, createRes.createdAt)
                    }
                }
            }
        }
        return binding.root
    }
}