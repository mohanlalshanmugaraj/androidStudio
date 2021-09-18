package com.example.zetranexample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zetranexample.database.PunchInOut;
import com.example.zetranexample.databinding.ListItemReportsBinding;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<PunchInOut> mPunchInOutList;

    public EmployeeAdapter(List<PunchInOut> employeeList) {
        mPunchInOutList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemReportsBinding reportsBinding = ListItemReportsBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new EmployeeViewHolder(reportsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeAdapter.EmployeeViewHolder holder, int position) {
        PunchInOut punchInOut = mPunchInOutList.get(position);
        holder.binding.setPio(punchInOut);
    }

    @Override
    public int getItemCount() {
            return mPunchInOutList == null ? 0 : mPunchInOutList.size();
    }

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {
        ListItemReportsBinding binding;

        public EmployeeViewHolder(@NonNull ListItemReportsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }
}
