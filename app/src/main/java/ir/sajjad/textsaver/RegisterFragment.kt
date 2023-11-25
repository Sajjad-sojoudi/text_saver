package ir.sajjad.textsaver

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import ir.sajjad.textsaver.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.edtNumber.editText!!.addTextChangedListener {
            if (it!!.length == 11) {
                binding.edtNumber.error = null
            } else {
                binding.edtNumber.error = "شماره موبایل صحیح نمی باشد"
            }
        }

        binding.edtPassword.editText!!.addTextChangedListener {
            if (it!!.length < 8) {
                binding.edtPassword.error = "گذرواژه باید حداقل 8 کاراکتر باشد"
            } else {
                binding.edtPassword.error = null
            }
        }

        val sharedPreferencesFileName = "data"
        val sharedPreferences = requireActivity().getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        binding.btnSubmit.setOnClickListener {

            val name = binding.edtName.editText!!.text.toString()
            sharedPreferences.edit().putString("name",name).apply()

            val birthday = binding.edtBirthday.editText!!.text.toString()
            sharedPreferences.edit().putString("birthday",birthday).apply()

            val phoneNumber = binding.edtNumber.editText!!.text.toString()
            sharedPreferences.edit().putString("phoneNumber",phoneNumber).apply()

            val instagram = binding.edtInstagramId.editText!!.text.toString()
            sharedPreferences.edit().putString("instagram",instagram).apply()

            val email = binding.edtEmail.editText!!.text.toString()
            sharedPreferences.edit().putString("email",email).apply()

            val password = binding.edtPassword.editText!!.text.toString()
            sharedPreferences.edit().putString("password",password).apply()

            binding.edtName.editText!!.text = null
            binding.edtBirthday.editText!!.text = null
            binding.edtNumber.editText!!.text = null
            binding.edtInstagramId.editText!!.text = null
            binding.edtEmail.editText!!.text = null
            binding.edtPassword.editText!!.text = null
        }



    }


}