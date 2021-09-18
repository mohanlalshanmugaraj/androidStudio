package com.example.database_room.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.database_room.Database.Note;
import com.example.database_room.Database.PunchInOut;
import com.example.database_room.R;
import com.example.database_room.Reports_Activity;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context mcontext;
    List<PunchInOut> mlist;

    public void setNoteAdapter(List<PunchInOut> lists){
        this.mlist = lists;
    }

    public NoteAdapter(Context context) {
        this.mcontext = context;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.aName.setText(mlist.get(position).getName());
        holder.adate.setText(mlist.get(position).getDate());
        holder.apunchIn.setText("PUNCH-IN  :"+" "+mlist.get(position).getPunchIn());
        holder.apunchOut.setText("PUNCH-OUT :"+mlist.get(position).getPunchOut());
    }
    @Override
    public int getItemCount() {
        return  mlist == null ? 0 : mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView aName, adate, apunchIn,apunchOut;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            aName=itemView.findViewById(R.id.rv_name);
            adate=itemView.findViewById(R.id.rv_date);
            apunchIn=itemView.findViewById(R.id.rv_punchIntime);
            apunchOut=itemView.findViewById(R.id.rv_punchOuttime);
        }
    }
}
