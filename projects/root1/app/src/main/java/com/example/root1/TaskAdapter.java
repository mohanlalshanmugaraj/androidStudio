package com.example.root1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> mTasks;
    private Context mContext;
    public TaskAdapter(Context context,List<Task>tasks){
        mContext=context;
        mTasks=tasks;
    }

    @NonNull
    @Override
    public TaskAdapter.TaskViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_task,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  TaskAdapter.TaskViewHolder holder, int position) {
        Task t =mTasks.get(position);
        holder.textViewTask.setText(t.getTask());
        holder.textViewDesc.setText(t.getDesc());
        holder.textViewFinishBy.setText(t.getFinishBy());

        if(t.isFinished()){
            holder.textViewStatus.setText("completed");
        }
        else {
            holder.textViewStatus.setText("not complected");
        }

    }

    @Override
    public int getItemCount() {

        return mTasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;
        public TaskViewHolder(@NonNull  View itemView) {
            super(itemView);

            textViewStatus = itemView.findViewById(R.id.textViewStatus);
            textViewTask = itemView.findViewById(R.id.textViewTask);
            textViewDesc = itemView.findViewById(R.id.textViewDesc);
            textViewFinishBy = itemView.findViewById(R.id.textViewFinishBy);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Task task = mTasks.get(getAdapterPosition());

                    Intent intent = new Intent(mContext, UpdateTask.class);
                    intent.putExtra("task", task);

                    mContext.startActivity(intent);
                }
            });
        }
    }
}
