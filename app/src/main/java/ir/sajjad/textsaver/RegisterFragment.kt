package ir.sajjad.textsaver

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

    }


}