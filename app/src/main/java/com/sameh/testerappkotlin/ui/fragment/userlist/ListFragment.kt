package com.sameh.testerappkotlin.ui.fragment.userlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sameh.testerappkotlin.R
import com.sameh.testerappkotlin.databinding.FragmentListBinding
import com.sameh.testerappkotlin.model.entity.User
import com.sameh.testerappkotlin.ui.adapter.OnItemClick
import com.sameh.testerappkotlin.ui.adapter.UserRecyclerView
import java.net.InetAddress

class ListFragment : Fragment(), OnItemClick {

    private lateinit var binding: FragmentListBinding
    private var username: String? = null
    private val userRecyclerView: UserRecyclerView by lazy {
        UserRecyclerView()
    }
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        username = arguments?.getString("userName")
        // instance from view model
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        // Recycler View
        binding.myRcv.adapter = userRecyclerView

        getAllUsers()

        binding.btnAdd.setOnClickListener {
                val message = binding.edtAddMessage.text.toString()
                userViewModel.addUserAPI(
                    User
                        (
                        10,
                        username.toString(),
                        message,
                        R.drawable.profile
                    )
                )
                getAllUsers()
                Toast.makeText(requireContext(), "$message added", Toast.LENGTH_SHORT)
                    .show()
                binding.edtAddMessage.setText("")
        }

        userRecyclerView.onItemClick = this

        userViewModel.addUserAPILiveData.observe(viewLifecycleOwner,
            Observer {
                if (it != null) {
                    Toast.makeText(requireContext(), "${it.userName} added", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(requireContext(), "Connection failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )

        userViewModel.userApiLiveData.observe(viewLifecycleOwner,
            Observer {
                if (it != null) {
                    userRecyclerView.setList(it)
                    binding.progressBar.visibility = View.GONE
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Connection failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }

    private fun getAllUsers() {
        // userViewModel.getAllUsers()
        userViewModel.getUsersFromAPI()
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onClick(user: User) {
        // userViewModel.deleteUser(user)
        userViewModel.deleteAPIUser(user.userId)
        Toast.makeText(
            requireContext(),
            "user: ${user.userName} is deleted ",
            Toast.LENGTH_SHORT
        ).show()
        getAllUsers()
    }

}