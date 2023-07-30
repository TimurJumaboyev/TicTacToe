package com.example.tictactoejava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class AddPlayers extends AppCompatActivity {

    ImageView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);


        LinearLayout linearLayout=findViewById(R.id.liner_main);
        AnimationDrawable animationDrawable=(AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        final EditText playerOne=findViewById(R.id.playerOneName);
        final EditText playerTwo=findViewById(R.id.playerTwoName);
        final TextView startGameBtn=findViewById(R.id.startBtn);

        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                YoYo.with(Techniques.RubberBand).duration(1000).repeat(1).playOn(title);
                final String getPlayerOneName=playerOne.getText().toString();
                final String getPlayerTwoName=playerTwo.getText().toString();

                if(getPlayerOneName.isEmpty()|| getPlayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayers.this,"Please Enter player names",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent=new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne",getPlayerOneName);
                    intent.putExtra("playerTwo",getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });
    }
}