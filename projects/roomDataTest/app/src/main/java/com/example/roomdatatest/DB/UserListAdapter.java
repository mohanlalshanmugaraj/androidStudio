package com.example.roomdatatest.DB;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatatest.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.myViewHolder> {
    private Context mContext;
    private List<User> mUserList;

    public UserListAdapter(Context context){
        this.mContext=context;
    }
    public void setUserList(List<User> userList){
        this.mUserList=userList;
        notifyDataSetChanged();
    }

    @NonNull

    @Override
    public UserListAdapter.myViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.recycler_row,parent,false);

        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserListAdapter.myViewHolder holder, int position) {
        holder.tname.setText(this.mUserList.get(position).name);
        holder.tid.setText(this.mUserList.get(position).id);
        holder.tdept.setText(this.mUserList.get(position).dept);


    }

    @Override
    public int getItemCount() {
        return this.mUserList.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder{
        TextView tname,tid,tdept;
        public myViewHolder(View view){
            super(view);
             tname=view.findViewById(R.id.tvname);
             tid=view.findViewById(R.id.tvid);
             tdept=view.findViewById(R.id.tvdept);


        }
    }


}
