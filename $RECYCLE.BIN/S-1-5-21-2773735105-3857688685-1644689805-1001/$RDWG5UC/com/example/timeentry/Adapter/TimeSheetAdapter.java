package com.example.timeentry.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeentry.Database.TimeSheets;
import com.example.timeentry.databinding.RvLayoutBinding;

import java.sql.Time;
import java.util.List;

public class TimeSheetAdapter extends RecyclerView.Adapter<TimeSheetAdapter.TimeSheetViewHolder> {
    Context mContext;
    List<TimeSheets> mList;


    public void setTimeSheetAdapter(List<TimeSheets> lists){
        this.mList=lists;
    }
    public TimeSheetAdapter(Context context){
        this.mContext=context;
    }
    @NonNull
    @Override
    public TimeSheetAdapter.TimeSheetViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        RvLayoutBinding mrvLayoutBinding = RvLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TimeSheetViewHolder(mrvLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull  TimeSheetAdapter.TimeSheetViewHolder holder, int position) {
        TimeSheets timeSheets =mList.get(position);
        holder.mBinding.setTimesheets(timeSheets);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class TimeSheetViewHolder extends RecyclerView.ViewHolder {
        RvLayoutBinding mBinding;
        public TimeSheetViewHolder(@NonNull RvLayoutBinding itemView) {
            super(itemView.getRoot());
            mBinding=itemView;


        }
    }
}
