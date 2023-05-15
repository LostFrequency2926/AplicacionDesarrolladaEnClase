package com.danielfmunoz.myfirstform.ui.bottomMenu.mySeries

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danielfmunoz.myfirstform.databinding.FragmentMySeriesBinding
import com.danielfmunoz.myfirstform.databinding.FragmentProfileBinding
import com.danielfmunoz.myfirstform.ui.model.Serie

class MySeriesFragment : Fragment() {

    private var _binding: FragmentMySeriesBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mySeriesViewModel: MySeriesViewModel
    private lateinit var seriesAdapter: SeriesAdapter
    private var seriesList: ArrayList<Serie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mySeriesViewModel = ViewModelProvider(this)[MySeriesViewModel::class.java]

        _binding = FragmentMySeriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.newSerieFloatingActionButton.setOnClickListener {
            findNavController().navigate(MySeriesFragmentDirections.actionNavigationMySeriesToNewSerieFragment2())
        }

        seriesAdapter = SeriesAdapter(seriesList,
        onItemClicked = {serie -> Log.d("nombre", serie.name!!)})

        binding.seriesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MySeriesFragment.requireContext())
            adapter = seriesAdapter
            setHasFixedSize(false)
        }

        mySeriesViewModel.loadSeries()

        mySeriesViewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        mySeriesViewModel.seriesList.observe(viewLifecycleOwner) { seriesList ->
            seriesAdapter.appendItems(seriesList)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}