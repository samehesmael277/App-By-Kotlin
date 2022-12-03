package com.sameh.testerappkotlin.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.sameh.testerappkotlin.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    // Global Variables
    private val USERNAME = "Sameh"
    private val PASSWORD = "Sameh123"

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            checkErrorsAndLogin()
        }
    }

    private fun sendUserName() {
        val action = LoginFragmentDirections.actionLoginFragmentToListFragment(USERNAME)
        findNavController().navigate(action)
    }

    private fun checkErrorsAndLogin() {
        if (!binding.edtUsername.text.isNullOrEmpty() && !binding.edtPassword.text.isNullOrEmpty()) {
            if (binding.edtUsername.text.toString() == USERNAME && binding.edtPassword.text.toString() == PASSWORD) {

                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()

                sendUserName()

            }
            else if (binding.edtUsername.text.toString() != USERNAME && binding.edtPassword.text.toString() != PASSWORD) {
                binding.edtUsername.error = "You enter wrong username"
                binding.edtPassword.error = "You enter wrong password"
            }
            else if (binding.edtUsername.text.toString() != USERNAME) {
                binding.edtUsername.error = "You enter wrong username"
            }
            else {
                binding.edtPassword.error = "You enter wrong password"
            }
        }
        else if (binding.edtUsername.text.isNullOrEmpty() && binding.edtPassword.text.isNullOrEmpty()) {
            binding.edtUsername.error = "You must enter username"
            binding.edtPassword.error = "You must enter password"
        } else if (binding.edtUsername.text.isNullOrEmpty()) {
            binding.edtUsername.error = "You must enter user name"
        } else {
            binding.edtPassword.error = "You must enter password"
        }
    }

}