package com.example.numberguessgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int attempt, num , guess;
    public final int max_attempt = 3;
    Button check, try_again_button, play_again_button;
    View try_again, success;
    String guess_string;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attempt=0;
        guess();
    }

    public void guess(){
        reload();
        check = findViewById(R.id.check_button);
        final View try_again = findViewById(R.id.try_again);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText =  findViewById(R.id.editText);
                guess_string = editText.getText().toString();
                if(guess_string.equals(""))
                    Toast.makeText(getApplicationContext(), "Please enter a guess", Toast.LENGTH_SHORT).show();
                else {
                    guess = Integer.parseInt(guess_string);
                    attempt++;
                    check(guess, num);
                }
                editText.getText().clear();

            }
        });
        try_again_button = findViewById(R.id.try_again_button);
        try_again_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try_again.setVisibility(View.GONE);
                reload();
            }
        });
        play_again_button = findViewById(R.id.play_again_button);
        success = findViewById(R.id.success);
        play_again_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success.setVisibility(View.GONE);
                reload();
            }
        });

    }
    public void reload()
    {
        attempt = 0;
        guess =0;
        Random rand = new Random();
        num=rand.nextInt(20);
        EditText editText =  findViewById(R.id.editText);
        editText.getText().clear();
        findViewById(R.id.check_button).setVisibility(View.VISIBLE);

    }
    public void check(int guess, int num){
        success = findViewById(R.id.success);
        try_again = findViewById(R.id.try_again);
        check = findViewById(R.id.check_button);
        if(num==guess) {
            success.setVisibility(View.VISIBLE);
            check.setVisibility(View.GONE);


        }
        else {
            if (attempt == max_attempt) {
                try_again.setVisibility(View.VISIBLE);
                check.setVisibility(View.GONE);
                return;
            }
            if (guess < num)
                Toast.makeText(getApplicationContext(), "Higher!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Lower!", Toast.LENGTH_SHORT).show();

        }



    }
}

