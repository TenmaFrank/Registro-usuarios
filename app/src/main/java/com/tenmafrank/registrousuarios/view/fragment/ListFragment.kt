package com.tenmafrank.registrousuarios.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenmafrank.registrousuarios.R
import com.tenmafrank.registrousuarios.databinding.FragmentListBinding
import com.tenmafrank.registrousuarios.model.DataModel
import com.tenmafrank.registrousuarios.utils.sharedpreferences.UserApplication.Companion.preferences
import com.tenmafrank.registrousuarios.view.recyclerview.UserAdapter
import com.tenmafrank.registrousuarios.viewmodel.ListViewModel
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: UserAdapter
    private var userDatas = mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                activity?.finish()
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list,container,false)

        viewLifecycleOwner.lifecycleScope.launch{
            try {
                userDatas = viewModel.doUserList() as MutableList<DataModel>
            }
            finally {
                initRecyclerView()
            }
        }

        with(binding){
            if(preferences.getNameUser().isEmpty()){
                appbarText.setText(preferences.getNameUser())
            }
            else{
                appbarText.setText(preferences.getEmailUser())
            }

            signOutButton.setOnClickListener {
                activity?.finish()
            }

            createButton.setOnClickListener {
                view?.findNavController()?.navigate(R.id.action_listFragment_to_createFragment)
            }

        }

        return binding.root
    }

    private suspend fun initRecyclerView(){
        adapter = UserAdapter(userDatas)
        binding.usersRecycler.layoutManager = LinearLayoutManager(binding.root.context)
        binding.usersRecycler.adapter = adapter
    }
}