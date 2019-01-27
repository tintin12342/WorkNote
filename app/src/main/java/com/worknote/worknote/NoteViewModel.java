package com.worknote.worknote;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class NoteViewModel extends AndroidViewModel {

    private NoteReposetory reposetory;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        reposetory = new NoteReposetory(application);
        allNotes = reposetory.getAllNotes();
    }

    public void insert(Note note){
        reposetory.insert(note);
    }

    public void update(Note note){
        reposetory.update(note);
    }

    public void delete(Note note){
        reposetory.delete(note);
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
