package com.danielfmunoz.myfirstform.ui.bottomMenu.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.databinding.FragmentMySeriesBinding

class SeriesFragment : Fragment() {

    private var _binding: FragmentMySeriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val seriesViewModel =
            ViewModelProvider(this).get(SeriesViewModel::class.java)

        _binding = FragmentMySeriesBinding.inflate(inflater, container, false)


        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}