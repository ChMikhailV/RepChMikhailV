package com.example.project_application.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.project_application.R

class ScalesFragment : Fragment(R.layout.scales_layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.toFinishButton).setOnClickListener {
            findNavController().navigate(R.id.action_scalesFragment_to_finishFragment)
        }
    }
}
