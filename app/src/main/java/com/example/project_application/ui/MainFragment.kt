package com.example.project_application.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_application.R
import androidx.recyclerview.widget.RecyclerView
import com.example.project_application.DatabaseHelper
import com.example.project_application.UserInfo
import com.example.project_application.UserAdapter
import com.example.project_application.UserInfoDatabaseHelper

class MainFragment : Fragment(R.layout.main_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        view.findViewById<Button>(R.id.go_next_button).setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_scalesFragment)
        }

    }
}
