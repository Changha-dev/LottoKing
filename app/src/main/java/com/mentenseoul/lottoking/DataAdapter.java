package com.mentenseoul.lottoking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    SQLiteDatabase database;

    String tableName;
    List<Ball> list;

    Context context;

    public DataAdapter(List<Ball> list){
        this.list = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        ImageView imageView5;
        ImageView imageView6;
        ImageView plusButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            imageView = view.findViewById(R.id.imageView);
            imageView2 = view.findViewById(R.id.imageView2);
            imageView3 = view.findViewById(R.id.imageView3);
            imageView4 = view.findViewById(R.id.imageView4);
            imageView5 = view.findViewById(R.id.imageView5);
            imageView6 = view.findViewById(R.id.imageView6);
            plusButton = view.findViewById(R.id.plusButton);
        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ball_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        context = viewGroup.getContext();
        return holder;

        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(""+(holder.getAdapterPosition() + 1));
        holder.imageView.setImageResource(list.get(position).image1);
        holder.imageView2.setImageResource(list.get(position).image2);
        holder.imageView3.setImageResource(list.get(position).image3);
        holder.imageView4.setImageResource(list.get(position).image4);
        holder.imageView5.setImageResource(list.get(position).image5);
        holder.imageView6.setImageResource(list.get(position).image6);

        holder.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(context)
                        .setTitle("저장")
                        .setMessage("저장 하시겠습니까?")
                        .setPositiveButton("네", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(database == null){
                                    createDatabase("database5");
                                    createTable("table5");
                                }

                                insertRecord(holder.getAdapterPosition());
                                list.remove(holder.getAdapterPosition());
                                notifyDataSetChanged();

                            }
                        })
                        .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                AlertDialog msgDlg = msgBuilder.create();
                msgDlg.show();
            }


        });
    };

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void createDatabase(String name){
        database = context.openOrCreateDatabase(name, MODE_PRIVATE, null);
    }

    private void createTable(String name){
        tableName = name;
        database.execSQL("create table if not exists " + name + " ("
        + " _id integer PRIMARY KEY autoincrement, "
        + "dateView1 long, "
        + "imageView1 integer, "
        + "imageView2 integer, "
        + "imageView3 integer, "
        + "imageView4 integer, "
        + "imageView5 integer, "
        + "imageView6 integer)");
    }


    private void insertRecord(int num) {
        int _num = num;
       Ball getBall = list.get(_num);

        long mNow = System.currentTimeMillis();
        //일단 날것 그대로 가져가자

        database.execSQL("insert into " + tableName
        + "(dateView1, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6) "
        + " values "
        + "(" + mNow + ", " + getBall.image1 + ", " + getBall.image2 + ", "+ getBall.image3 + ", "+ getBall.image4 + ", "+ getBall.image5 + ", "+ getBall.image6  + ")");

    }



}

