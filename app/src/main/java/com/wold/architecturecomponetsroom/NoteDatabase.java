package com.wold.architecturecomponetsroom;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase noteDatabaseInstance;
    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){

        if(noteDatabaseInstance==null){
            noteDatabaseInstance= Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,
                    "note_database").fallbackToDestructiveMigration().addCallback(roomCall).build();
        }
        return noteDatabaseInstance;
    }
    private static RoomDatabase.Callback roomCall=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDatabaseAsync(noteDatabaseInstance).execute();
        }
    };
    private static class populateDatabaseAsync extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;
        private populateDatabaseAsync(NoteDatabase db){
            noteDao=db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("title 1","descripition 1",1));
            noteDao.insert(new Note("title 2","descripition 2",2));
            noteDao.insert(new Note("title 3","descripition 3",3));
            return null;
        }
    }
}
