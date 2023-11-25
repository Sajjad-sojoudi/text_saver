package ir.sajjad.textsaver

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.sajjad.textsaver.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferencesFileName = "data"
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        val name = sharedPreferences.getString("name", "")
        val birthday = sharedPreferences.getString("birthday", "")
        val phoneNumber = sharedPreferences.getString("phoneNumber", "")
        val instagram = sharedPreferences.getString("instagram", "")
        val email = sharedPreferences.getString("email", "")
        val password = sharedPreferences.getString("password", "")

        binding.txtTitle.text = name
        binding.txtName.text = name
        binding.txtBirthday.text = birthday
        binding.txtPhoneNumber.text = phoneNumber
        binding.txtInstagram.text = instagram
        binding.txtEmail.text = email
        binding.txtPassword.text = password


    }


}