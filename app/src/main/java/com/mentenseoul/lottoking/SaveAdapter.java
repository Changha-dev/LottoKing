package com.mentenseoul.lottoking;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SaveAdapter extends RecyclerView.Adapter<SaveAdapter.ViewHolder> {

    private CursorAdapter mCursorAdapter;
    private Context mContext;
    private ViewHolder holder;

    int _id;
    ImageView minusButton;

    public SaveAdapter(Context context, Cursor c) {
        mContext = context;
        mCursorAdapter = new CursorAdapter(mContext, c, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
                View v = LayoutInflater.from(context).inflate(R.layout.save_item, viewGroup, false);
                return v;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {


                _id = cursor.getInt(cursor.getColumnIndex("_id"));

                // 여기서 날짜 변환
                long now = cursor.getLong(cursor.getColumnIndex("dateView1"));
                Date mDate =new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
                SimpleDateFormat sdf3 = new SimpleDateFormat("aaa");
                String getTime = sdf.format(mDate);
                String getTime2 = sdf2.format(mDate);
                String getTime3 = sdf3.format(mDate);

                // 커서에서 이미지 가져오기
                int imageView1 =cursor.getInt(cursor.getColumnIndex("imageView1"));
                int imageView2 =cursor.getInt(cursor.getColumnIndex("imageView2"));
                int imageView3 =cursor.getInt(cursor.getColumnIndex("imageView3"));
                int imageView4 =cursor.getInt(cursor.getColumnIndex("imageView4"));
                int imageView5 =cursor.getInt(cursor.getColumnIndex("imageView5"));
                int imageView6 =cursor.getInt(cursor.getColumnIndex("imageView6"));
                minusButton = view.findViewById(R.id.minusButton);

                holder.textView.setText(getTime);
                holder.textView2.setText(getTime2);
                holder.textView3.setText(getTime3);
                holder.imageView.setImageResource(imageView1);
                holder.imageView2.setImageResource(imageView2);
                holder.imageView3.setImageResource(imageView3);
                holder.imageView4.setImageResource(imageView4);
                holder.imageView5.setImageResource(imageView5);
                holder.imageView6.setImageResource(imageView6);


            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView2;
        TextView textView3;
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        ImageView imageView5;
        ImageView imageView6;
        ImageView minusButton;
        public ViewHolder(@NonNull View view) {
            super(view);
            textView = view.findViewById(R.id.textView);
            textView2 = view.findViewById(R.id.textView2);
            textView3 = view.findViewById(R.id.textView3);
            imageView = view.findViewById(R.id.imageView);
            imageView2 = view.findViewById(R.id.imageView2);
            imageView3 = view.findViewById(R.id.imageView3);
            imageView4 = view.findViewById(R.id.imageView4);
            imageView5 = view.findViewById(R.id.imageView5);
            imageView6 = view.findViewById(R.id.imageView6);
            minusButton = view.findViewById(R.id.minusButton);

        }
    }

    @NonNull
    @Override
    public  ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), viewGroup);
        holder = new ViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        mCursorAdapter.getCursor().moveToPosition(position);
        mCursorAdapter.bindView(holder.itemView, mContext, mCursorAdapter.getCursor());
        Cursor cursor = mCursorAdapter.getCursor(); // 이부분 매우 중요 안그러면 => Error : android.database.CursorIndexOutOfBoundsException: Index 1 requested, with a size of 1
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder msgBuilder = new AlertDialog.Builder(mContext)
                    .setTitle("삭제")
                    .setMessage("삭제 하시겠습니까?")
                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //                Toast.makeText(mContext, "" + position, Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(position);
                            cursor.moveToPosition(position);
                            int test = cursor.getInt(0);
//                Toast.makeText(mContext, "" + test, Toast.LENGTH_SHORT).show();
                            String dbPath = "/data/data" + "/com.mentenseoul.lottoking" + "/databases/database5";
                            SQLiteDatabase database = SQLiteDatabase.openDatabase(dbPath,null, SQLiteDatabase.OPEN_READWRITE );
                            database.execSQL("delete from " + "table5" + " where _id = " + test);
                            Cursor cursor2 = database.rawQuery("select _id, dateView1, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6 from table5", null);
                            mCursorAdapter.swapCursor(cursor2);
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
    }

    @Override
    public int getItemCount() {
        return mCursorAdapter.getCount();
    }

}


