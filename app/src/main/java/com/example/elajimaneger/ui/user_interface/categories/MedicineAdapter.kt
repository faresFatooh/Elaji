package com.example.elajimaneger.ui.user_interface.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.elajimaneger.R
import com.example.elajimaneger.databinding.ItemMedicineBinding

class MedicineAdapter(private val itemList: List<Medicine>, private var context: Context) :
    RecyclerView.Adapter<MedicineAdapter.MyViewHolder>() {

//    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        val tvMedicineSalary: TextView = itemView.findViewById(R.id.tvMedicineSalary)
//        val tvMedicineName: TextView = itemView.findViewById(R.id.tvMedicineName)
//        val ivMedicine: ImageView = itemView.findViewById(R.id.ivMedicine)
//        val buAddMedicineToFavorite: ImageView = itemView.findViewById(R.id.buAddMedicineToFavorite)
//
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemMedicineBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listModels = itemList[position]
        holder.bind(listModels,context)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class MyViewHolder(private var binding: ItemMedicineBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {

        fun bind(model: Medicine, context: Context) {
            binding.apply {
                Glide.with(context).load(model.image).into(ivMedicine)
                tvMedicineName.text = model.name
                tvMedicineSalary.text = "${model.price}$"

                setUpFavoriteIcon(model)

                buAddMedicineToFavorite.setOnClickListener {
                    model.isAdded = !model.isAdded!!
                    setUpFavoriteIcon(model)
                }
            }
        }

        private fun setUpFavoriteIcon(model: Medicine) {
            if (model.isAdded == true) {
                binding.buAddMedicineToFavorite.setImageResource(R.drawable.ic_delete_image)
            } else {
                binding.buAddMedicineToFavorite.setImageResource(R.drawable.ic_add_image)
            }
        }
    }


}
