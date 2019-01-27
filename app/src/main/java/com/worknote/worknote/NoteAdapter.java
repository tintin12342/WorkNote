package com.worknote.worknote;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return true;
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);

        holder.weekDate.setText("Week: " + currentNote.getWeekDate());
        holder.lastUpdated.setText("Updated: " + currentNote.getLastUpdated());

        if (currentNote.getM()) {
            holder.m.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.m.setTextColor(Color.parseColor("#4A6572"));
        }

        if (currentNote.getTu()) {
            holder.tu.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.tu.setTextColor(Color.parseColor("#4A6572"));
        }

        if (currentNote.getW()) {
            holder.w.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.w.setTextColor(Color.parseColor("#4A6572"));
        }

        if (currentNote.getTh()) {
            holder.th.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.th.setTextColor(Color.parseColor("#4A6572"));
        }

        if (currentNote.getF()) {
            holder.f.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.f.setTextColor(Color.parseColor("#4A6572"));
        }

        if (currentNote.getSa()) {
            holder.st.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.st.setTextColor(Color.parseColor("#4A6572"));
        }

        if (currentNote.getSu()) {
            holder.su.setTextColor(Color.parseColor("#F9AA33"));
        } else {
            holder.su.setTextColor(Color.parseColor("#4A6572"));
        }
    }

    public Note getNoteAt(int position) {
        return getItem(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView weekDate;
        private TextView lastUpdated;
        private TextView m, tu, w, th, f, st, su;

        public NoteHolder(View itemView) {
            super(itemView);

            weekDate = itemView.findViewById(R.id.weekDate);
            lastUpdated = itemView.findViewById(R.id.lastUpdated);
            m = itemView.findViewById(R.id.m);
            tu = itemView.findViewById(R.id.tu);
            w = itemView.findViewById(R.id.w);
            th = itemView.findViewById(R.id.th);
            f = itemView.findViewById(R.id.f);
            st = itemView.findViewById(R.id.sa);
            su = itemView.findViewById(R.id.su);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClicked(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClicked(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
