package com.mentenseoul.lottoking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class Fragment1 extends Fragment {

    RecyclerView recyclerView;

    DataAdapter dataAdapter;

    List<Ball> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container,false);

        // 광고 넣기

        TextView textView = rootView.findViewById(R.id.textView);
        //빈 화면일 때
        textView.setVisibility(list.size() > 0 ? textView.GONE : textView.VISIBLE);

//        list = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        dataAdapter = new DataAdapter(list);
        recyclerView.setAdapter(dataAdapter);


        FloatingActionButton fab = rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TreeSet<Integer> set = new TreeSet<>();
                while(set.size() < 6){
                    int random = new Random().nextInt(45);
                    set.add(random);
                }
                ArrayList Aset = new ArrayList(set);

                int tmpId1 = getResources().getIdentifier(
                        "lotto" + ((Integer)Aset.get(0) + 1), "drawable", getActivity().getPackageName());
                int tmpId2 = getResources().getIdentifier(
                        "lotto" + ((Integer)Aset.get(1) + 1), "drawable", getActivity().getPackageName());
                int tmpId3 = getResources().getIdentifier(
                        "lotto" + ((Integer)Aset.get(2) + 1), "drawable", getActivity().getPackageName());
                int tmpId4 = getResources().getIdentifier(
                        "lotto" + ((Integer)Aset.get(3) + 1), "drawable", getActivity().getPackageName());
                int tmpId5 = getResources().getIdentifier(
                        "lotto" + ((Integer)Aset.get(4) + 1), "drawable", getActivity().getPackageName());
                int tmpId6 = getResources().getIdentifier(
                        "lotto" + ((Integer)Aset.get(5) + 1), "drawable", getActivity().getPackageName());
                Ball setBall =  new Ball(tmpId1, tmpId2, tmpId3, tmpId4, tmpId5, tmpId6);

                list.add(setBall);
                //빈화면 아닐 때
                textView.setVisibility(list.size() > 0 ? textView.GONE : textView.VISIBLE);

                dataAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(dataAdapter);
            }
        });
        
        return rootView;
    }
}
