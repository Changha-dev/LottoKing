package com.mentenseoul.lottoking;

import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

public class Roulette extends Fragment implements Animation.AnimationListener {

    boolean blnButtonRotation = true;
    long lngDegrees = 0;

    ImageView selected,imageRoulette;

    ImageView buttonStart;

    TextView textView;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;
    TextView textView6;
    int cnt;

    Button button;
    Button button2;
    ArrayList<Integer> arrayList = new ArrayList<>();
    SQLiteDatabase database;
    String tableName;


    SoundPool soundPool;
    int soundID;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_roulette, container,  false);

        buttonStart = (ImageView) rootView.findViewById(R.id.buttonStart);
        selected = (ImageView) rootView.findViewById(R.id.imageSelected);
        imageRoulette = (ImageView) rootView.findViewById(R.id.rouletteImage);
        textView = rootView.findViewById(R.id.textView);
        textView2 = rootView.findViewById(R.id.textView2);
        textView3 = rootView.findViewById(R.id.textView3);
        textView4 = rootView.findViewById(R.id.textView4);
        textView5 = rootView.findViewById(R.id.textView5);
        textView6 = rootView.findViewById(R.id.textView6);

        button = rootView.findViewById(R.id.button);
        button.setVisibility(View.GONE);
        button2 = rootView.findViewById(R.id.button2);
        button2.setVisibility(View.GONE);

        // Sound Effect
        soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(getContext(), R.raw.roulettewheel, 1);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonRotation(rootView);
                soundPool.play(soundID, 1f, 1f, 0, 0, 1f);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int tmp1 = getResources().getIdentifier(
                        "lotto" + (arrayList.get(0)), "drawable", getActivity().getPackageName());
                int tmp2 = getResources().getIdentifier(
                        "lotto" + (arrayList.get(1)), "drawable", getActivity().getPackageName());
                int tmp3 = getResources().getIdentifier(
                        "lotto" + (arrayList.get(2)), "drawable", getActivity().getPackageName());
                int tmp4 = getResources().getIdentifier(
                        "lotto" + (arrayList.get(3)), "drawable", getActivity().getPackageName());
                int tmp5 = getResources().getIdentifier(
                        "lotto" + (arrayList.get(4)), "drawable", getActivity().getPackageName());
                int tmp6 = getResources().getIdentifier(
                        "lotto" + (arrayList.get(5)), "drawable", getActivity().getPackageName());



                if(database == null){
                    createDatabase("database5");
                    createTable("table5");
                }
                insertRecord(tmp1, tmp2, tmp3, tmp4, tmp5, tmp6);
                arrayList.removeAll(arrayList);
                textView.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
                buttonStart.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.removeAll(arrayList);
                textView.setText("");
                textView2.setText("");
                textView3.setText("");
                textView4.setText("");
                textView5.setText("");
                textView6.setText("");
                buttonStart.setVisibility(View.VISIBLE);
                button.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
            }
        });

        return rootView;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        this.blnButtonRotation = false;
        buttonStart.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        int num =(int) (45 - Math.floor(((double) this.lngDegrees) / 8));
        Toast.makeText(getContext(),"" + num, Toast.LENGTH_LONG).show();
        this.blnButtonRotation = true;
        buttonStart.setVisibility(View.VISIBLE);

        cnt += 1;
        if(cnt == 1){
            arrayList.add(0, num);
            textView.setText(String.valueOf(arrayList.get(0)));
        }else if (cnt == 2){
            arrayList.add(1, num);
            textView2.setText(String.valueOf(arrayList.get(1)));
        }else if (cnt == 3){
            arrayList.add(2, num);
            textView3.setText(String.valueOf(arrayList.get(2)));
        }else if (cnt == 4){
            arrayList.add(3, num);
            textView4.setText(String.valueOf(arrayList.get(3)));;
        }
        else if (cnt == 5){
            arrayList.add(4, num);
            textView5.setText(String.valueOf(arrayList.get(4)));
        }
        else if (cnt == 6){
            arrayList.add(5, num);
            textView6.setText(String.valueOf(arrayList.get(5)));
            cnt = 0;
            buttonStart.setVisibility(View.GONE);
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void onClickButtonRotation(View v)
    {
        if(this.blnButtonRotation)
        {

            int ran = new Random().nextInt(360) + 3600; //10번 돌리고 + 0~360

            RotateAnimation rotateAnimation = new RotateAnimation((float)this.lngDegrees, (float)
                    (this.lngDegrees + ((long)ran)), 1,0.5f,1,0.5f);

            this.lngDegrees = (this.lngDegrees + ((long)ran)) % 360;
            rotateAnimation.setDuration((long)ran);
            rotateAnimation.setFillAfter(true);
            rotateAnimation.setInterpolator(new DecelerateInterpolator());
            rotateAnimation.setAnimationListener(this);
            imageRoulette.setAnimation(rotateAnimation);
            imageRoulette.startAnimation(rotateAnimation);

        }
    }

    private void createDatabase(String name){
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

    private void insertRecord(int tmp1, int tmp2, int tmp3, int tmp4, int tmp5, int tmp6) {

        long mNow = System.currentTimeMillis();

        database.execSQL("insert into " + tableName
                + "(dateView1, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6) "
                + " values "
                + "(" + mNow + ", " + tmp1 + ", " + tmp2 + ", "+ tmp3 + ", "+ tmp4 + ", "+ tmp5 + ", "+ tmp6  + ")");

    }
}
