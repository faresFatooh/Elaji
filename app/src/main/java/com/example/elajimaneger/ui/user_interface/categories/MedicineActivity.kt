package com.example.elajimaneger.ui.user_interface.categories

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elajimaneger.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MedicineActivity : AppCompatActivity() {
    private lateinit var itemList: ArrayList<Medicine>
    private lateinit var adapter: MedicineAdapter
    private lateinit var database: DatabaseReference
    private lateinit var categories: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medicine)
        categories = intent.extras!!.getString("MedicineName").toString()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMedicine)
        val spanCount = 2
        val layoutManager = GridLayoutManager(this, spanCount, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        itemList = ArrayList()
        adapter = MedicineAdapter(itemList, this)
        recyclerView.adapter = adapter

        database = FirebaseDatabase.getInstance().reference.child("medicine")

        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
            override fun onDataChange(datasnapshot: DataSnapshot) {

                if (datasnapshot.exists()) {
                    (itemList).clear()
                    for (snapshot in datasnapshot.children) {
                        val notification = snapshot.getValue(Medicine::class.java)
                        if (categories == notification!!.categories)
                            (itemList).add(notification!!)

                    }
                    itemList.reverse()
                    adapter.notifyDataSetChanged()
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

    }
}