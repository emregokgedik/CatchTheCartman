package com.emregokgedik.catchthekennyv3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class PlayActivity extends AppCompatActivity {
    TextView scoreText;
    int time;
    int score;
    ImageView kenny1;
    ImageView kenny2;
    ImageView kenny3;
    ImageView kenny4;
    ImageView kenny5;
    ImageView kenny6;
    ImageView kenny7;
    ImageView kenny8;
    ImageView cartman1;
    ImageView cartman2;
    ImageView cartman3;
    ImageView cartman4;
    ImageView cartman5;
    ImageView cartman6;
    ImageView cartman7;
    ImageView cartman8;
    ImageView[] kennyArray;
    ImageView[] cartmanArray;
    Runnable runnable;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        scoreText=findViewById(R.id.scoreText);
        kenny1=findViewById(R.id.kenny1);
        kenny2=findViewById(R.id.kenny2);
        kenny3=findViewById(R.id.kenny3);
        kenny4=findViewById(R.id.kenny4);
        kenny5=findViewById(R.id.kenny5);
        kenny6=findViewById(R.id.kenny6);
        kenny7=findViewById(R.id.kenny7);
        kenny8=findViewById(R.id.kenny8);
        cartman1=findViewById(R.id.cartman1);
        cartman2=findViewById(R.id.cartman2);
        cartman3=findViewById(R.id.cartman3);
        cartman4=findViewById(R.id.cartman4);
        cartman5=findViewById(R.id.cartman5);
        cartman6=findViewById(R.id.cartman6);
        cartman7=findViewById(R.id.cartman7);
        cartman8=findViewById(R.id.cartman8);
        cartmanArray=new ImageView[] {cartman1,cartman2,cartman3,cartman4,cartman5,cartman6,cartman7,cartman8};
        kennyArray=new ImageView[] {kenny1,kenny2,kenny3,kenny4,kenny5,kenny6,kenny7,kenny8};
        time=0;
        score=0;
        hideImages();

    }
    public void cartmanClick(View view){
    score++;
    scoreText.setText("SCORE: "+score);
    }
    public void kennyClick(View view){
        Intent intent=new Intent(this, MainActivity.class);
        intent.putExtra("lose",true);
        intent.putExtra("lastScore",score);
        startActivity(intent);

    }
    public void hideImages(){
        handler=new Handler(Looper.getMainLooper());

        runnable=new Runnable(){

            @Override
            public void run() {
                for(ImageView image : cartmanArray){
                    image.setVisibility(View.INVISIBLE);
                }
                for(ImageView image : kennyArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random=new Random();
                int i= random.nextInt(7);
                kennyArray[i].setVisibility(View.VISIBLE);
                cartmanArray[7-i].setVisibility(View.VISIBLE);
                handler.postDelayed(PlayActivity.this::hideImages,500);
            }
        };
        handler.post(runnable);
    }
    public void restart(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}