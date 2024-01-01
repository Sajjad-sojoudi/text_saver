package ir.sajjad.textsaver.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import ir.sajjad.textsaver.R
import ir.sajjad.textsaver.databinding.FragmentShowTextBinding

class ShowTextFragment : Fragment() {

    private lateinit var binding: FragmentShowTextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // دریافت اطلاعات از Bundle
        val txtSubject = arguments?.getString("subject")
        val txtDetail = arguments?.getString("detail")

        // نمایش اطلاعات در EditText
        binding.editTextSubject.setText(txtSubject)
        binding.editTextDetail.setText(txtDetail)

        // دکمه برای انتقال به HomeFragment
        binding.btnEdit.setOnClickListener {
            val txtSubject = binding.editTextSubject.text.toString()
            val txtDetail = binding.editTextDetail.text.toString()

            // ذخیره تغییرات در SharedPreferences
            saveTextToSharedPreferences(requireContext(), txtSubject, txtDetail)

            // انتقال به HomeFragment
            findNavController().navigate(R.id.action_showTextFragment_to_homeFragment)
        }
    }

    private fun saveTextToSharedPreferences(
        context: Context,
        txtSubject: String,
        txtDetail: String
    ) {
        val sharedPreferencesFileName = "text_posts"
        val sharedPreferences =
            context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.putString("subject", txtSubject)
        editor.putString("detail", txtDetail)
        editor.apply()
    }
}

