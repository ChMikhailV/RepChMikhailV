package com.example.project_application.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_application.DatabaseHelper
import com.example.project_application.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment(R.layout.login_screen) {

    private lateinit var loginEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginEditText = view.findViewById(R.id.loginId)
        passwordEditText = view.findViewById(R.id.loginPass)
        loginButton = view.findViewById(R.id.loginButton)

        dbHelper = DatabaseHelper(requireContext())

        loginButton.setOnClickListener {
            val login = loginEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(requireContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                if (login == "admin" && password == "admin") {
                    Toast.makeText(requireContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    Toast.makeText(requireContext(), "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}
