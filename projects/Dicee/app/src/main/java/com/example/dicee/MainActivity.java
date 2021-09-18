package com.example.dicee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button rollbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rollbtn=findViewById(R.id.rollButton);
        final ImageView leftDice=(ImageView)findViewById(R.id.leftDice);
        final ImageView rightDice=(ImageView)findViewById(R.id.rightDice);
        final int[] diceArray={
                R.drawable.dice1,
                R.drawable.dice2,
                R.drawable.dice3,
                R.drawable.dice4,
                R.drawable.dice5,
                R.drawable.dice6};

        rollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random=new Random();
                int number =random.nextInt(6);
                leftDice.setImageResource(diceArray[number]);
                number=random.nextInt(6);
                rightDice.setImageResource(diceArray[number]);

            }
        });

    }
}