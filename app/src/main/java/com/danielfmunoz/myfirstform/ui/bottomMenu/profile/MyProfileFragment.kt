package com.danielfmunoz.myfirstform.ui.bottomMenu.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.danielfmunoz.myfirstform.databinding.FragmentProfileBinding
import com.danielfmunoz.myfirstform.ui.main.SignInActivity
import com.danielfmunoz.myfirstform.ui.main.SignInViewModel

class MyProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var myProfileViewModel: MyProfileViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        myProfileViewModel =
            ViewModelProvider(this)[MyProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        myProfileViewModel.errorMsg.observe(viewLifecycleOwner) { errorMsg ->
            Toast.makeText(requireActivity(), errorMsg, Toast.LENGTH_SHORT).show()
        }

        myProfileViewModel.userLoaded.observe(viewLifecycleOwner) { user ->
            with(binding){
                nameTextView.text = user?.name
                emailTextView.text = user?.email
            }
        }

        myProfileViewModel.loadUserInfo()

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