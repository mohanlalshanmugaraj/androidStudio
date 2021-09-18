package com.example.texttospach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

import javax.net.ssl.SSLEngineResult;

public class MainActivity extends AppCompatActivity{
    TextToSpeech mTextToSpeech;
    int result;
    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //button1=findViewById(R.id.start);
        mTextToSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i ==TextToSpeech.SUCCESS) {
                   result = mTextToSpeech.setLanguage(Locale.ENGLISH);
                }
                    else{
                        Toast.makeText(MainActivity.this, "Feature not supported", Toast.LENGTH_SHORT).show();
                    }

            }
        });

    }


    public void dosomething(View view) {
        switch (view.getId()){
            case R.id.start;

            if (result ==TextToSpeech.LANG_NOT_SUPPORTED || )
        }
    }
}