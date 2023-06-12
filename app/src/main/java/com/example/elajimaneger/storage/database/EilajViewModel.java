package com.example.elajimaneger.storage.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.elajimaneger.storage.database.tables.Category;
import com.example.elajimaneger.storage.database.tables.Eilaj;

import java.util.List;



public class EilajViewModel extends AndroidViewModel {
    private final Repository repository;

    public EilajViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    //*Eilaj*
    public void insertManyEilaj(Eilaj... eilaj) {
        repository.insertManyEilaj(eilaj);
    }

    public LiveData<List<Eilaj>> getAllEilaj() {
        return repository.getAllEilaj();
    }

    //*Category*
    public void insertManyCategory(Category... categories) {
        repository.insertManyCategory(categories);
    }

    public LiveData<List<Category>> getAllCategories() {
        return repository.getAllCategories();
    }

}
