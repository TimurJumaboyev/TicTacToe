package com.example.tictactoejava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     private final List<int[]> combinationsList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    public static MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3;
    public static boolean isSoundEnable = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout=findViewById(R.id.relative_main);
        AnimationDrawable animationDrawable=(AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(2000);
        animationDrawable.start();

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);
        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);
        image1 = findViewById(R.id.img1);
        image2 = findViewById(R.id.img2);
        image3 = findViewById(R.id.img3);
        image4 = findViewById(R.id.img4);
        image5 = findViewById(R.id.img5);
        image6 = findViewById(R.id.img6);
        image7 = findViewById(R.id.img7);
        image8 = findViewById(R.id.img8);
        image9 = findViewById(R.id.img9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});

        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(0)) {
                    performAction((ImageView) v,0);
                    startSound();
                }
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(1)) {
                    performAction((ImageView) v,1);
                    startSound();
                }
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(2)) {
                    performAction((ImageView) v,2);
                    startSound();
                }
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(3)) {
                    performAction((ImageView) v,3);
                    startSound();
                }
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(4)) {
                    performAction((ImageView) v,4);
                    startSound();
                }
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(5)) {
                    performAction((ImageView) v,5);
                    startSound();
                }
            }
        });
        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(6)) {
                    performAction((ImageView) v,6);
                    startSound();
                }
            }
        });
        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(7)) {
                    performAction((ImageView) v,7);
                    startSound();
                }
            }
        });
        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelectable(8)) {
                    performAction((ImageView) v,8);
                    startSound();
                }
            }
        });


    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.red_x);
            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this, playerOneName.getText().toString() + " has won the match", MainActivity.this);
                startWinSound();
                winDialog.setCancelable(false);
                winDialog.show();
                
            } else if (totalSelectedBoxes == 9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "It is a draw!!!", MainActivity.this);
                startErrorSound();
                winDialog.setCancelable(false);
                winDialog.show();

            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.yellow_0);

            if(checkPlayerWin()){
                WinDialog winDialog = new WinDialog(MainActivity.this, playerTwoName.getText().toString() + " has won the match", MainActivity.this);
                startWinSound();
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (selectedBoxPosition==9) {
                WinDialog winDialog = new WinDialog(MainActivity.this, "It is a draw!!!", MainActivity.this);
                startErrorSound();
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;
        if (playerTurn == 1) {
            playerTwoLayout.setBackgroundResource(R.drawable.white_two_color);
            playerOneLayout.setBackgroundResource(R.drawable.purple_green_color);
        } else {
            playerOneLayout.setBackgroundResource(R.drawable.white_two_color);
            playerTwoLayout.setBackgroundResource(R.drawable.purple_green_color);
        }
    }


    private boolean checkPlayerWin() {
        boolean response = false;
        for (int i = 0; i < combinationsList.size(); i++) {
            final int[] combination = combinationsList.get(i);
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn && boxPositions[combination[2]] == playerTurn) {
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false;

        if (boxPositions[boxPosition] == 0) {
            response = true;
        }
        return response;
    }
    public void restartMatch(){
        boxPositions=new int[]{0,0,0,0,0,0,0,0,0};
        playerTurn=1;
        totalSelectedBoxes=1;
        image1.setImageResource(R.drawable.white_two_color);
        image2.setImageResource(R.drawable.white_two_color);
        image3.setImageResource(R.drawable.white_two_color);
        image4.setImageResource(R.drawable.white_two_color);
        image5.setImageResource(R.drawable.white_two_color);
        image6.setImageResource(R.drawable.white_two_color);
        image7.setImageResource(R.drawable.white_two_color);
        image8.setImageResource(R.drawable.white_two_color);
        image9.setImageResource(R.drawable.white_two_color);

    }

    final void startSound() {
        if (isSoundEnable) {
            mediaPlayer1 = MediaPlayer.create( this,R.raw.tick);
            mediaPlayer1.start();
        }
    }
    final void startErrorSound() {
        if (isSoundEnable) {
            mediaPlayer2 = MediaPlayer.create(this, R.raw.error);
            mediaPlayer2.start();
        }
    }
    final void startWinSound() {
        if (isSoundEnable) {
            mediaPlayer3 = MediaPlayer.create(this, R.raw.win_sound);
            mediaPlayer3.start();
        }
    }
}