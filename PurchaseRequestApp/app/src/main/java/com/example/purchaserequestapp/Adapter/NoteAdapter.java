package com.example.purchaserequestapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.purchaserequestapp.Database.DbController;
import com.example.purchaserequestapp.Database.Note;
import com.example.purchaserequestapp.MainActivity;
import com.example.purchaserequestapp.R;
import com.example.purchaserequestapp.Update_Activity;
import com.example.purchaserequestapp.ViewRequest_Activity;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Executors;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    Context mContext;
    List<Note> mList;

    private OnDeleteClickListener onDeleteClickListener;


    public interface OnDeleteClickListener
    {
        void OnDeleteClickListener(Note note);
    }

    public void setNoteAdapter(List<Note> lists){
        this.mList=lists;
    }
    public NoteAdapter(Context context){
        this.mContext=context;
    }
    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_layout, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull  NoteAdapter.ViewHolder holder, int position) {
        holder.requestnum.setText("Req-"+mList.get(position).getRequestno());
        holder.date.setText(mList.get(position).getDate());
        holder.laptop.setText("laptop :"+mList.get(position).getLaptop());
        holder.mouse.setText("mouse :"+mList.get(position).getMouse());
        holder.keyboard.setText("keyboard :"+mList.get(position).getKeyboard());
        holder.mobile.setText("mobile :"+mList.get(position).getMobile());

    }

    @Override
    public int getItemCount() {
        return  mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView requestnum,date,laptop,mouse,keyboard,mobile;
        Button close,edit;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            requestnum=itemView.findViewById(R.id.rv_req_num);
            date=itemView.findViewById(R.id.rv_date);
            laptop=itemView.findViewById(R.id.rv_laptop);
            mouse=itemView.findViewById(R.id.rv_mouse);
            keyboard=itemView.findViewById(R.id.rv_keyboard);
            mobile=itemView.findViewById(R.id.rv_mobile);
            edit=itemView.findViewById(R.id.rv_edit);
            close=itemView.findViewById(R.id.rv_close);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Executors.newSingleThreadExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                Note note =mList.get(getAdapterPosition());

                                DbController.getInstance(mContext).getDao().delectData(note);
                                new Handler((Looper.getMainLooper())).post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mList.remove(note);
                                        notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                }

            });


            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note note = mList.get(getAdapterPosition());
                    Intent intent =new Intent(mContext,Update_Activity.class);
                    intent.putExtra("note", note);
                    Log.d("id","Button pressed");
                    mContext.startActivity(intent);
                }
            });

        }
        }
    }

