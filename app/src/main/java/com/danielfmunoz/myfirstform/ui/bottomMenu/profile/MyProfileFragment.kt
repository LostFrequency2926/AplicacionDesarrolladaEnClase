package com.danielfmunoz.myfirstform.ui.bottomMenu.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.databinding.FragmentProfileBinding
import com.danielfmunoz.myfirstform.ui.main.SignInActivity
import com.danielfmunoz.myfirstform.ui.main.SignInViewModel

class MyProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var viewModel: MyProfileViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myProfileViewModel =
            ViewModelProvider(this)[MyProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.buttonSignOut.setOnClickListener{
            myProfileViewModel.signOut()
            val intent = Intent(activity, SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        return root



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}