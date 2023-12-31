package com.example.elajimaneger.storage.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.elajimaneger.storage.database.tables.Eilaj

@Dao
interface EilajDao {
    @Query("SELECT * FROM Eilaj")
    fun getAll(): LiveData<List<Eilaj>>

    @Query("SELECT * FROM Eilaj WHERE eid IN (:eilajIds)")
    fun loadAllByIds(eilajIds: IntArray): LiveData<List<Eilaj>>

    @Query(
        "SELECT * FROM Eilaj WHERE name LIKE :name AND " +
                "category LIKE :category LIMIT 1"
    )
    fun findByNameAndCategory(name: String, category: String): Eilaj

    @Insert
    fun insertAll(vararg eilaj: Eilaj)

    @Delete
    fun delete(eilaj: Eilaj)
}