package com.example.elajimaneger.ui.user_interface.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeViewModel : ViewModel() {

    private val itemList: MutableLiveData<List<Categories>> = MutableLiveData()

    init {
        val database = FirebaseDatabase.getInstance().reference.child("items")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val itemList = ArrayList<Categories>()
                val itemListLiveData: MutableLiveData<List<Categories>> = MutableLiveData()

                for (postSnapshot in dataSnapshot.children) {
                    val itemData = postSnapshot.getValue(Categories::class.java)
                    itemData?.let { itemList.add(it) }
                }

                itemListLiveData.postValue(itemList)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Error handling
            }
        })

    }

    fun getItemList(): LiveData<List<Categories>> {
        return itemList
    }
}




