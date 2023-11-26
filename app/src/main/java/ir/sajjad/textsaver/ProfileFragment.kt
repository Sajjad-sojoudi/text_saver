package ir.sajjad.textsaver

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.sajjad.textsaver.databinding.DialogEditDataBinding
import ir.sajjad.textsaver.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sharedPreferencesFileName = "data"
        val sharedPreferences =
            requireActivity().getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

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

        binding.btnEdit.setOnClickListener {
            showEditDialog()
        }


    }

    fun showEditDialog() {
        val dialogBinding = DialogEditDataBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(true)
            .create()


        dialogBinding.dialogEdtName.editText!!.setText(binding.txtName.text)
        dialogBinding.dialogEdtBirthday.editText!!.setText(binding.txtBirthday.text)
        dialogBinding.dialogEdtPhoneNumber.editText!!.setText(binding.txtPhoneNumber.text)
        dialogBinding.dialogEdtInstagram.editText!!.setText(binding.txtInstagram.text)
        dialogBinding.dialogEdtEmail.editText!!.setText(binding.txtEmail.text)
        dialogBinding.dialogEdtPassword.editText!!.setText(binding.txtPassword.text)


        dialogBinding.btnUpdate.setOnClickListener {

            val sharedPreferencesFileName = "data"
            val sharedPreferences = requireActivity().getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)
            sharedPreferences.edit().putString("name",dialogBinding.dialogEdtName.editText!!.text.toString()).apply()
            sharedPreferences.edit().putString("birthday",dialogBinding.dialogEdtBirthday.editText!!.text.toString()).apply()
            sharedPreferences.edit().putString("phoneNumber",dialogBinding.dialogEdtPhoneNumber.editText!!.text.toString()).apply()
            sharedPreferences.edit().putString("instagram",dialogBinding.dialogEdtInstagram.editText!!.text.toString()).apply()
            sharedPreferences.edit().putString("email",dialogBinding.dialogEdtEmail.editText!!.text.toString()).apply()
            sharedPreferences.edit().putString("password",dialogBinding.dialogEdtPassword.editText!!.text.toString()).apply()

            binding.txtTitle.text = dialogBinding.dialogEdtName.editText!!.text.toString()
            binding.txtName.text = dialogBinding.dialogEdtName.editText!!.text.toString()
            binding.txtBirthday.text = dialogBinding.dialogEdtBirthday.editText!!.text.toString()
            binding.txtPhoneNumber.text = dialogBinding.dialogEdtPhoneNumber.editText!!.text.toString()
            binding.txtInstagram.text = dialogBinding.dialogEdtInstagram.editText!!.text.toString()
            binding.txtEmail.text = dialogBinding.dialogEdtEmail.editText!!.text.toString()
            binding.txtPassword.text = dialogBinding.dialogEdtPassword.editText!!.text.toString()

            dialog.dismiss()
        }

        dialogBinding.btnCancel.setOnClickListener {
            dialog.dismiss()
        }



        dialog.show()
    }
}


