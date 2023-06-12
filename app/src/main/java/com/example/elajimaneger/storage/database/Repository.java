package com.example.elajimaneger.storage.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.elajimaneger.storage.database.daos.CategoryDao;
import com.example.elajimaneger.storage.database.daos.EilajDao;
import com.example.elajimaneger.storage.database.tables.Category;
import com.example.elajimaneger.storage.database.tables.Eilaj;

import java.util.List;



public class Repository {
    private final EilajDao eilajDao;
    private final CategoryDao categoryDao;

    public Repository(Application application) {
        EilajiDatabase db = EilajiDatabase.getDatabase(application);
        eilajDao = db.eilajDao();
        categoryDao = db.categoryDao();
    }

    public void insertManyEilaj(Eilaj... eilaj) {
        EilajiDatabase.databaseWriteExecutor.execute(() -> eilajDao.insertAll(eilaj));
    }

    public LiveData<List<Eilaj>> getAllEilaj() {
        return eilajDao.getAll();
    }

    public void insertManyCategory(Category... categories) {
        EilajiDatabase.databaseWriteExecutor.execute(() -> categoryDao.insertAll(categories));
    }

    public LiveData<List<Category>> getAllCategories() {
        return categoryDao.getAll();
    }
}
