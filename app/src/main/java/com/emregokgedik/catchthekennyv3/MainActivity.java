package com.emregokgedik.catchthekennyv3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static java.sql.Types.NULL;

public class MainActivity extends AppCompatActivity {
    TextView highestScoreText;
    TextView lastScoreText;
    int lastScore;
    int highestScore;
    boolean lose;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences=this.getSharedPreferences("com.emregokgedik.storingdata", Context.MODE_PRIVATE);
        highestScoreText=findViewById(R.id.highestScoreText);
        lastScoreText=findViewById(R.id.lastScoreText);
        Intent intent=getIntent();
        lose = intent.getBooleanExtra("lose",false);
        lastScore = intent.getIntExtra("lastScore",0);
        highestScore=sharedPreferences.getInt("highestScore",0);
        sharedPreferences.edit().putInt("lastScore",lastScore).apply();
        highestScoreText.setText("Highest Score: "+highestScore);
        if(lose){
            if(lastScore>highestScore){
                highestScoreText.setText("Highest Score: "+lastScore);
                sharedPreferences.edit().putInt("highestScore",lastScore).apply();
            }
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setTitle("Game Over");
            alert.setMessage("Game is over.\nTry to do not click the Kyle.");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            alert.show();
        }
        lastScoreText.setText("Last Score: "+lastScore);
    }
    public void play(View view){
        Intent intent=new Intent(MainActivity.this,PlayActivity.class);
        finish();
        startActivity(intent);
    }
}