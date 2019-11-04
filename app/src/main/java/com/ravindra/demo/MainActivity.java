package com.ravindra.demo;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 == Yelllow and 1== Red
    int activePlayer = 0;
    boolean gameIsActive = true;
    // 2 is unplayed

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {6, 7, 8},
            {0, 4, 8}, {2, 4, 6}
                                };
    public void dropIn(View view){
        try{
            ImageView counter = (ImageView) view;

            Log.i("info", counter.getTag().toString());

            int tappedCounter = Integer.parseInt(counter.getTag().toString());

            if(gameState[tappedCounter] == 2 && gameIsActive){
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1000f);
                if(activePlayer == 0){
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                }else{
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1000f).setDuration(300);

                for(int[] winningPostion : winningPositions){
                    if(gameState[winningPostion[0]] == gameState[winningPostion[1]] &&
                            gameState[winningPostion[1]] == gameState[winningPostion[2]] &&
                            gameState[winningPostion[0]] != 2){
                        String winner = "Red";

                        if(gameState[winningPostion[0]] == 0){
                            winner = "Yellow";
                        }
                        //someone has won !
                        gameIsActive = false;

                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessageTextView);
                        winnerMessage.setText(winner + " has won!!");
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                    else{
                        boolean gameIsOver = true ;
                        for(int countState : gameState){
                            if( countState == 2)
                                gameIsOver = false;
                        }

                        if(gameIsOver){
                            TextView winnerMessage = (TextView) findViewById(R.id.winnerMessageTextView);
                            winnerMessage.setText("It's a draw!!");
                            LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                            layout.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
        catch(Exception e){
            Log.i("info", String.valueOf(e));
        }
    }

    public void playAgain(View view){
        try{
            gameIsActive = true;
            LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
            layout.setVisibility(View.INVISIBLE);
            activePlayer = 0;

            for(int i = 0; i < 9; i++){
                gameState[i] = 2;
            }

            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout2);

            for( int i = 0 ; i < gridLayout.getChildCount(); i++){
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }
        }
        catch (Exception e){
            Log.i("info", String.valueOf(e));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
