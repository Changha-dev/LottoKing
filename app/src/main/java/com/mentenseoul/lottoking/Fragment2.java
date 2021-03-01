package com.mentenseoul.lottoking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class Fragment2 extends Fragment {



    RecyclerView recyclerView2;

    SaveAdapter saveAdapter;

    TextView textView;
    TextView textView2;
    TextView textView4;
    ImageView getImage;
    ImageView getImage2;
    ImageView getImage3;
    ImageView getImage4;
    ImageView getImage5;
    ImageView getImage6;
    ImageView getImage7;

    SQLiteDatabase database;

    String tableName;

    //asyncTask
    GetLottoTask getLottoTask;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        textView = rootView.findViewById(R.id.textView);
        textView2 = rootView.findViewById(R.id.textView2);
        textView4 = rootView.findViewById(R.id.textView4);
        getImage = rootView.findViewById(R.id.imageView);
        getImage2 = rootView.findViewById(R.id.getImage2);
        getImage3 = rootView.findViewById(R.id.getImage3);
        getImage4 = rootView.findViewById(R.id.getImage4);
        getImage5 = rootView.findViewById(R.id.getImage5);
        getImage6 = rootView.findViewById(R.id.getImage6);
        getImage7 = rootView.findViewById(R.id.getImage7);

        // Thread 구현

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView2 = rootView.findViewById(R.id.recyclerView2);
        recyclerView2.setLayoutManager(layoutManager);

        String dbPath = "/data/data" + "/com.mentenseoul.lottoking" + "/databases/database5";
        if(database == null){
            createDatabase("database5");
            createTable("table5");
        }
        database = SQLiteDatabase.openDatabase(dbPath,null, SQLiteDatabase.OPEN_READWRITE );

        // 갱신
        Cursor cursor = database.rawQuery("select _id, dateView1, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6 from table5", null);
        saveAdapter = new SaveAdapter(getContext(), cursor);
        recyclerView2.setAdapter(saveAdapter);


        return rootView;
    }

    private void createDatabase (String name){
        database = getContext().openOrCreateDatabase(name, MODE_PRIVATE, null);
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

    @Override
    public void onResume() {
        super.onResume();
        getLottoTask = new GetLottoTask();
        getLottoTask.execute();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{ //화면 전환할 때 비정상 종료 방지
            if(getLottoTask.getStatus() == AsyncTask.Status.RUNNING){
                getLottoTask.cancel(true);
            }
            else {

            }
        } catch (Exception e){

        }
    }

    public class GetLottoTask extends AsyncTask<Void, Void, Map<String, String>> {

        @Override
        protected Map<String, String> doInBackground(Void... params) {
            Map<String, String> result = new HashMap<String, String>();
            try{
                Document doc = Jsoup.connect("https://dhlottery.co.kr/common.do?method=main&mainMode=default").get();
                Elements contents = doc.select("#lottoDrwNo");          //회차 id값 가져오기

                result.put("numbers", "제 " + contents.text() + " 회");

                Elements data = doc.select("#drwNoDate");
                result.put("data", data.text());

                Elements money = doc.select("#winnerId > dl > dd > strong");
                result.put("money", money.text());


                for(int i =1; i < 7; i++){
                    Elements text =doc.select("#drwtNo" + i);
                    result.put("num" + i, text.text());

                }
                Elements bnu = doc.select("#bnusNo");
                result.put("num7", bnu.text());

            }catch (IOException e){
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(Map<String, String> map) {
            textView.setText(map.get("numbers"));
            textView2.setText(map.get("data"));
            textView4.setText(map.get("money"));
            int tmpId1 = getResources().getIdentifier(
                    "lotto" + map.get("num1"), "drawable", getActivity().getPackageName());
            int tmpId2 = getResources().getIdentifier(
                    "lotto" + map.get("num2"), "drawable", getActivity().getPackageName());
            int tmpId3 = getResources().getIdentifier(
                    "lotto" + map.get("num3"), "drawable", getActivity().getPackageName());
            int tmpId4 = getResources().getIdentifier(
                    "lotto" + map.get("num4"), "drawable", getActivity().getPackageName());
            int tmpId5 = getResources().getIdentifier(
                    "lotto" + map.get("num5"), "drawable", getActivity().getPackageName());
            int tmpId6 = getResources().getIdentifier(
                    "lotto" + map.get("num6"), "drawable", getActivity().getPackageName());
            int tmpId7 = getResources().getIdentifier(
                    "lotto" + map.get("num7"), "drawable", getActivity().getPackageName());


            getImage.setImageResource(tmpId1);
            getImage2.setImageResource(tmpId2);
            getImage3.setImageResource(tmpId3);
            getImage4.setImageResource(tmpId4);
            getImage5.setImageResource(tmpId5);
            getImage6.setImageResource(tmpId6);
            getImage7.setImageResource(tmpId7);
        }
    }


}

