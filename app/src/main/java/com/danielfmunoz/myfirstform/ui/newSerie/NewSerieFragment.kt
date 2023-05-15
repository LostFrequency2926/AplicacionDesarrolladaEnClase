package com.danielfmunoz.myfirstform.ui.newSerie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.danielfmunoz.myfirstform.R
import com.danielfmunoz.myfirstform.databinding.FragmentNewSerieBinding

class NewSerieFragment : Fragment() {

    private lateinit var newSerieviewModel: NewSerieViewModel
    private lateinit var  newSerieBinding: FragmentNewSerieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        newSerieBinding = FragmentNewSerieBinding.inflate(inflater, container, false)
        newSerieviewModel = ViewModelProvider(this )[NewSerieViewModel::class.java]


        newSerieviewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireContext(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        newSerieviewModel.createSerieSucces.observe(viewLifecycleOwner) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        with(newSerieBinding){
            btnRegistrarSerie.setOnClickListener {
                newSerieviewModel.validateData(
                    etNombre.text.toString(),
                    etSeasons.text.toString(),
                    etSummary.text.toString(),
                    etRanking.text.toString(),
                    cbGenre1.isChecked,
                    cbGenre2.isChecked,
                    cbGenre3.isChecked,
                    cbGenre4.isChecked

                )
            }
        }

        return newSerieBinding.root
    }
}