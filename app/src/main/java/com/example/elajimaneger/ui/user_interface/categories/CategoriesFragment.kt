package com.example.elajimaneger.ui.user_interface.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.elajimaneger.R
import com.example.elajimaneger.ui.user_interface.home.Categories
import com.example.elajimaneger.ui.user_interface.home.CategorisAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CategoriesFragment : Fragment() {

    private lateinit var itemList: ArrayList<Categories>
    private lateinit var adapter: CategorisAdapter
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_categories, container, false)

        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerViewCategories)
        val spanCount = 2
        val layoutManager = GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        itemList = ArrayList()
        adapter = CategorisAdapter(itemList,requireContext())
        recyclerView.adapter = adapter

        database = FirebaseDatabase.getInstance().reference.child("items")

        database.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SuspiciousIndentation", "NotifyDataSetChanged")
            override fun onDataChange(datasnapshot: DataSnapshot) {

                if (datasnapshot.exists()) {
                    (itemList).clear()
                    for (snapshot in datasnapshot.children) {
                        val notification = snapshot.getValue(Categories::class.java)
                            (itemList).add(notification!!)

                    }
                    itemList.reverse()
                    adapter.notifyDataSetChanged()
                }


            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return rootView
    }


}