package ir.sajjad.textsaver.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ir.sajjad.textsaver.R
import ir.sajjad.textsaver.data.ItemTextPost
import ir.sajjad.textsaver.databinding.FragmentAddTextBinding

class AddTextFragment : Fragment() {
    private lateinit var binding: FragmentAddTextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddText.setOnClickListener {
            val title = binding.edtTitle.text.toString()
            val details = binding.edtDetails.text.toString()

            if (title.isNotEmpty() && details.isNotEmpty()) {
                val newTextPost = ItemTextPost(title, details)

                saveTextPostToSharedPreferences(requireContext(), newTextPost)

                val transaction = parentFragmentManager.beginTransaction()
                findNavController().navigate(R.id.action_addTextFragment_to_homeFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
    }

    private fun saveTextPostToSharedPreferences(context: Context, newTextPost: ItemTextPost) {
        val sharedPreferencesFileName = "text_posts"
        val sharedPreferences = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        val editor = sharedPreferences.edit()

        val newTextPostSet = HashSet<String>()
        newTextPostSet.addAll(sharedPreferences.getStringSet("text_posts", emptySet())!!) // دریافت مجموعه متنی قبلی
        newTextPostSet.add("${newTextPost.txtTitle} - ${newTextPost.txtDetails}") // افزودن متن جدید

        editor.putStringSet("text_posts", newTextPostSet)
        editor.apply()
    }
}
