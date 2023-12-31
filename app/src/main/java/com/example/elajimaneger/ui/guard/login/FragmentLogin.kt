package com.example.elajimaneger.ui.guard.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.elajimaneger.R
import com.example.elajimaneger.databinding.FragmentLoginBinding
import com.example.elajimaneger.ui.base.BaseActivity
import com.example.elajimaneger.ui.guard.signUp.FragmentSignUp
import com.google.android.material.snackbar.Snackbar


class FragmentLogin : Fragment() {
    private var binding: FragmentLoginBinding? = null
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupListeners()
        observeLoginResult()
    }

    private fun setupViewModel() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun setupListeners() {
        binding?.apply {
            buSignUp.setOnClickListener {
                val singUpFragment = FragmentSignUp()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.mainActivityContainer, singUpFragment)
                    .commitAllowingStateLoss()
            }

            buLogin.setOnClickListener {
                val email = edEmail.text.toString()
                val password = edPassword.text.toString()

                if (checkData(email, password)) {
                    loginViewModel.login(email, password, requireActivity())
                } else {
                    showTextError(email, password)
                }
            }
        }
    }

    private fun showTextError(email: String, password: String) {
        binding?.apply {
            edEmail.error = if (email.isEmpty()) "Please Enter Your Email" else null
            edPassword.error = if (password.isEmpty()) "Please Enter Your Password" else null
        }
    }

    private fun checkData(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }

    private fun observeLoginResult() {
        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResult ->
            when (loginResult) {
                is LoginResult.Success -> {
                    // Handle successful login
                    //TODO HomeActivity >> Base
                    startActivity(Intent(requireContext(), BaseActivity::class.java))
                    requireActivity().finish()
                }

                is LoginResult.Error -> {
                    // Handle login error
                    showSnackBar(loginResult.errorMessage)
                }
            }
        }
    }


    private fun showSnackBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}