package com.example.elajimaneger.storage.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.elajimaneger.storage.database.daos.CategoryDao;
import com.example.elajimaneger.storage.database.daos.EilajDao;
import com.example.elajimaneger.storage.database.tables.Category;
import com.example.elajimaneger.storage.database.tables.Eilaj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = {Eilaj.class, Category.class}, version = 1, exportSchema = false)
public abstract class EilajiDatabase extends RoomDatabase {

    public abstract EilajDao eilajDao();
    public abstract CategoryDao categoryDao();

    private static volatile EilajiDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static EilajiDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EilajiDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EilajiDatabase.class, "eilaj_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
