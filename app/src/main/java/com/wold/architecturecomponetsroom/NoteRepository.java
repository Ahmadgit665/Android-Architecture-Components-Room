package com.wold.architecturecomponetsroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase noteDatabase=NoteDatabase.getInstance(application);
        noteDao=noteDatabase.noteDao();
        allNotes=noteDao.getAllNodes();
    }

    // these methods are APIs that repository exposes to outside(insert,update,delete,deleteALl)
    public void insert(Note note){
        new insertAsynckTask(noteDao).execute(note);

    }
    public void update(Note note){
        new updateAsynckTask(noteDao).execute(note);

    }
    public void delete(Note note){

        new deleteAsynckTask(noteDao).execute(note);
    }
    public void deleteAllNotes(){

        new deleteAllNodesAsynckTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
    public static class insertAsynckTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private insertAsynckTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }
    public static class updateAsynckTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private updateAsynckTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
    public static class deleteAsynckTask extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;
        private deleteAsynckTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }
    public static class deleteAllNodesAsynckTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private deleteAllNodesAsynckTask(NoteDao noteDao){
            this.noteDao=noteDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNodes();
            return null;
        }
    }
}
