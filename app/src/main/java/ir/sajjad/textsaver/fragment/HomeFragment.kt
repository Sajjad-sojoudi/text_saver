package ir.sajjad.textsaver.fragment

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ir.sajjad.textsaver.R
import ir.sajjad.textsaver.adapter.TextSaverAdapter
import ir.sajjad.textsaver.data.ItemTextPost
import ir.sajjad.textsaver.databinding.DialogDeleteDataBinding
import ir.sajjad.textsaver.databinding.FragmentHomeBinding

class HomeFragment : Fragment(),TextSaverAdapter.TextEvent {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TextSaverAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAdd.setOnClickListener {
            val transaction = parentFragmentManager.beginTransaction()
            findNavController().navigate(R.id.action_homeFragment_to_addTextFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        adapter = TextSaverAdapter(arrayListOf(),this)

        binding.recyclerMain.adapter = adapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(requireContext())

        val textPostList = getTextPostListFromSharedPreferences(requireContext())

        if (textPostList.isNotEmpty()) {
            for (item in textPostList) {
                adapter.setData(item)
                binding.recyclerMain.scrollToPosition(0)
            }

        }

        val sharedPreferencesFileName = "text_posts"
        val sharedPreferences = requireContext().getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        val txtSubject = sharedPreferences.getString("subject", "")
        val txtDetail = sharedPreferences.getString("detail", "")

        // نمایش اطلاعات در HomeFragment




    }

    private fun getTextPostListFromSharedPreferences(context: Context): List<ItemTextPost> {
        val sharedPreferencesFileName = "text_posts"
        val sharedPreferences = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        val textPostSet = sharedPreferences.getStringSet("text_posts", emptySet())
        binding.recyclerMain.scrollToPosition(0)

        return textPostSet?.map {
            binding.recyclerMain.scrollToPosition(0)
            val parts = it.split(" - ")
            ItemTextPost(parts[0], parts[1])
        } ?: emptyList()

    }

    override fun onTextClicked(showTextPost: ItemTextPost) {
        // گرفتن مقادیر txtSubject و txtDetail
        val txtSubject = showTextPost.txtTitle
        val txtDetail = showTextPost.txtDetails

        // ذخیره مقادیر در SharedPreferences
        saveTextToSharedPreferences(txtSubject, txtDetail)

        // ایجاد Bundle برای انتقال اطلاعات به ShowTextFragment
        val bundle = Bundle().apply {
            putString("subject", txtSubject)
            putString("detail", txtDetail)
        }

        // انتقال به ShowTextFragment با استفاده از Bundle
        findNavController().navigate(R.id.action_homeFragment_to_showTextFragment, bundle)
    }

    private val sharedPreferencesFileName = "text_posts"

    private fun saveTextToSharedPreferences(txtSubject: String, txtDetail: String) {
        val sharedPreferences = context?.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        sharedPreferences?.edit()?.apply {
            putString("subject", txtSubject)
            putString("detail", txtDetail)
            apply()
        }
    }

    override fun onTextLongClicked(textPost: ItemTextPost, pos: Int) {
        val dialog = AlertDialog.Builder(context).create()
        val dialogDeleteBinding = DialogDeleteDataBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteBinding.btnNo.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.btnYes.setOnClickListener {
            dialog.dismiss()
            adapter.removeData(textPost, pos)

            removeFromSharedPreferences(textPost)


        }
    }

    private fun removeFromSharedPreferences(item: ItemTextPost) {
        val sharedPreferences = context?.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        val textPostSet = sharedPreferences?.getStringSet("text_posts", emptySet())?.toMutableSet()

        textPostSet?.remove("${item.txtTitle} - ${item.txtDetails}")

        sharedPreferences?.edit()?.putStringSet("text_posts", textPostSet)?.apply()
    }




}