package com.wold.architecturecomponetsroom;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;
    private String descripition;
    private int priority;

    public Note(String title, String descripition, int priority) {
        this.title = title;
        this.descripition = descripition;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescripition() {
        return descripition;
    }

    public int getPriority() {
        return priority;
    }
}
