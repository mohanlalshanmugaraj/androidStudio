package com.example.timesheet.adapter;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timesheet.activity.CreateTimesheetActivity;
import com.example.timesheet.activity.EditActivity;
import com.example.timesheet.database.DBController;
import com.example.timesheet.database.Timesheet;
import com.example.timesheet.databinding.ListItemBinding;

import java.util.List;
import java.util.concurrent.Executors;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.TimesheetViewHoder> {

    private List<Timesheet> mTimeSheet;

    public ViewAdapter(List<Timesheet> timeSheets) {
        mTimeSheet = timeSheets;
    }


    @Override
    public TimesheetViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new TimesheetViewHoder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter.TimesheetViewHoder holder, int position) {
        Timesheet timesheet = mTimeSheet.get(position);
        holder.itemBinding.setTimesheet(timesheet);
    }

    @Override
    public int getItemCount() {
        return mTimeSheet == null ? 0 : mTimeSheet.size();
    }

    public class TimesheetViewHoder extends RecyclerView.ViewHolder {
        ListItemBinding itemBinding;

        public TimesheetViewHoder(@NonNull ListItemBinding itemView) {
            super(itemView.getRoot());
            itemBinding = itemView;

            itemView.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Timesheet sheet = mTimeSheet.get(getAdapterPosition());

                    Intent edit = new Intent(itemView.getRoot().getContext(), EditActivity.class);
                    edit.putExtra("timesheet", sheet);
                    itemView.getRoot().getContext().startActivity(edit);
                }
            });

            itemView.ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteTimesheet();
                }
            });
        }

        private void deleteTimesheet() {
            Executors.newSingleThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Timesheet timesheet = mTimeSheet.get(getAdapterPosition());
                    DBController.getInstance(itemView.getContext()).getDao().deleteData(timesheet);

                    new Handler(Looper.getMainLooper()).post(() -> {
                        mTimeSheet.remove(timesheet);
                        notifyDataSetChanged();
                    });
                }
            });
        }

    }
}
