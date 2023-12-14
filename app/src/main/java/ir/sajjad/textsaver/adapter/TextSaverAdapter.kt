package ir.sajjad.textsaver.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.sajjad.textsaver.R
import ir.sajjad.textsaver.data.ItemTextPost

class TextSaverAdapter(private val data :List<ItemTextPost>):RecyclerView.Adapter<TextSaverAdapter.TextSaverViewHolder>() {
    inner class TextSaverViewHolder(itemView: View , private val context: Context):RecyclerView.ViewHolder(itemView){
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)
        val txtDetails = itemView.findViewById<TextView>(R.id.txt_details)

        fun bindData(position: Int){
            txtTitle.text = data[position].txtTitle
            txtDetails.text = data[position].txtDetails
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSaverViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text,parent,false)
        return TextSaverViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: TextSaverViewHolder, position: Int) {
        holder.bindData(position)
    }

}
