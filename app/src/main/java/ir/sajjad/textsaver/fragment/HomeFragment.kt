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

        // ایجاد آداپتور
        adapter = TextSaverAdapter(arrayListOf(),this)

        // تنظیم آداپتور برای RecyclerView
        binding.recyclerMain.adapter = adapter
        binding.recyclerMain.layoutManager = LinearLayoutManager(requireContext())

        // دریافت لیست از SharedPreferences
        val textPostList = getTextPostListFromSharedPreferences(requireContext())

        // اگر لیست اطلاعات موجود بود، آن‌ها را به RecyclerView اضافه کنید
        if (textPostList.isNotEmpty()) {
            for (item in textPostList) {
                adapter.setData(item)
            }
            binding.recyclerMain.scrollToPosition(0)
        }


    }

    private fun getTextPostListFromSharedPreferences(context: Context): List<ItemTextPost> {
        val sharedPreferencesFileName = "text_posts"
        val sharedPreferences = context.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        val textPostSet = sharedPreferences.getStringSet("text_posts", emptySet())
        binding.recyclerMain.scrollToPosition(0)

        return textPostSet?.map {
            val parts = it.split(" - ")
            ItemTextPost(parts[0], parts[1])
        } ?: emptyList()

    }

    override fun onTextClicked() {
        Toast.makeText(context, "clicked on text", Toast.LENGTH_SHORT).show()
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
            // حذف آیتم از لیست در Adapter
            dialog.dismiss()
            adapter.removeData(textPost, pos)

            // حذف آیتم از SharedPreferences
            removeFromSharedPreferences(textPost)


        }
    }

    private fun removeFromSharedPreferences(item: ItemTextPost) {
        val sharedPreferencesFileName = "text_posts"
        val sharedPreferences = context?.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE)

        // دریافت لیست از SharedPreferences
        val textPostSet = sharedPreferences?.getStringSet("text_posts", emptySet())

        // اگر لیست اطلاعات موجود بود، آن‌ها را به ArrayList تبدیل کنید
        val textPostList = textPostSet?.toMutableList() ?: mutableListOf()

        // حذف آیتم مورد نظر
        textPostList.remove("${item.txtTitle} - ${item.txtDetails}")

        // ذخیره لیست جدید در SharedPreferences
        sharedPreferences?.edit()?.putStringSet("text_posts", textPostList.toSet())?.apply()
    }



}