package com.wold.architecturecomponetsroom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter <NoteAdapter.NoteHolder>{

    private OnItemClickListener listener;

    private List<Note> notes= new ArrayList<>();

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.note_items,parent,false);
        return new NoteHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note  currentNote= notes.get(position);
        holder.title.setText(currentNote.getTitle());
        holder.descripition.setText(currentNote.getDescripition());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes){
        this.notes=notes;
        notifyDataSetChanged();
    }
    public Note getNoteat(int position){
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private TextView descripition;
        private TextView priority;


        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.textview_title);
            descripition= itemView.findViewById(R.id.textview_descripition);
            priority= itemView.findViewById(R.id.textview_priority);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(notes.get(position));
                }}
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
