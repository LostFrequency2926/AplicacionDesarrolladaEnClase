package com.danielfmunoz.myfirstform.ui.bottomMenu.mySeries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.databinding.FragmentMySeriesBinding
import com.danielfmunoz.myfirstform.databinding.FragmentProfileBinding

class MySeriesFragment : Fragment() {

    private var _binding: FragmentMySeriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mySeriesViewModel =
            ViewModelProvider(this).get(MySeriesViewModel::class.java)

        _binding = FragmentMySeriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}