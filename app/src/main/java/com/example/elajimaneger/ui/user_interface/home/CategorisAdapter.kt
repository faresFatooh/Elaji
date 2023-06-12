package com.example.elajimaneger.ui.user_interface.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elajimaneger.R
import com.example.elajimaneger.ui.user_interface.categories.MedicineActivity

class CategorisAdapter(private val itemList: List<Categories>, private val context: Context) :
    RecyclerView.Adapter<CategorisAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val eilaj_categoryTV: TextView = itemView.findViewById(R.id.tvPharmacyDepartment)
        val eilajCategoryIV: ImageView = itemView.findViewById(R.id.ivPharmacyDepartment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pharmacy_department, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.eilajCategoryIV.setOnClickListener {
            val intent = Intent(context, MedicineActivity::class.java)
            intent.putExtra("MedicineName",currentItem.name)
            context.startActivity(intent)
        }
        holder.eilaj_categoryTV.text = currentItem.name.toString()
        Glide.with(holder.itemView.context)
            .load(currentItem.image)
            .into(holder.eilajCategoryIV)


    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}


