package ir.sajjad.textsaver.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ir.sajjad.textsaver.R
import ir.sajjad.textsaver.data.ItemTextPost
import java.text.FieldPosition

class TextSaverAdapter(
    private val data: ArrayList<ItemTextPost>,
    private val textEvent: TextEvent,
) : RecyclerView.Adapter<TextSaverAdapter.TextSaverViewHolder>() {

    inner class TextSaverViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_title)
        val txtDetails = itemView.findViewById<TextView>(R.id.txt_details)

        fun bindData(position: Int) {
            if (position >= 0 && position < data.size) {
                val currentItem = data[position]

                txtTitle.text = currentItem.txtTitle
                txtDetails.text = currentItem.txtDetails

                itemView.setOnClickListener {
                    textEvent.onTextClicked(currentItem)
                }

                itemView.setOnLongClickListener {
                    textEvent.onTextLongClicked(currentItem, adapterPosition)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSaverViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
        return TextSaverViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TextSaverViewHolder, position: Int) {
        holder.bindData(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: ItemTextPost) {
        data.add(newData)
        notifyItemInserted(0)

    }

    fun removeData(oldData: ItemTextPost, oldPosition: Int) {
        data.remove(oldData)
        notifyItemRemoved(oldPosition)
    }

    interface TextEvent {
        fun onTextClicked(showTextPost :ItemTextPost)
        fun onTextLongClicked(textPost: ItemTextPost, pos: Int)
    }



}


