package com.task4.dogbreeds.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.task4.dogbreeds.Room.Dog;
import com.task4.dogbreeds.Room.DogDao;
import com.task4.dogbreeds.Room.DogDatabase;

import java.util.List;

public class Repository {
    private final DogDao dogDao;

    private final LiveData<List<Dog>> allDogs;

    public Repository(Application application)
    {
        DogDatabase database= DogDatabase.getInstance(application);
        dogDao= database.noteDao();
        allDogs= dogDao.getAllDogs();
    }
    public void insert(Dog dog)
    {
        new InsertDogAsyncTask(dogDao).execute(dog);
    }


    public LiveData<List<Dog>> getAllDogs() {
        return allDogs;
    }

    private static class InsertDogAsyncTask extends AsyncTask<Dog, Void, Void> {

        private final DogDao dogDao;
        private InsertDogAsyncTask(DogDao dogDao)
        {
            this.dogDao= dogDao;
        }

        @Override
        protected Void doInBackground(Dog... dogs) {
            dogDao.insert(dogs[0]);
            return null;
        }
    }
}
