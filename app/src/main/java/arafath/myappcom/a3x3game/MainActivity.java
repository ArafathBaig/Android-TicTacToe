package arafath.myappcom.a3x3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 means X turn : 1 means O turn : 2 means empty box
    boolean gameActive = true;
    int activePlayer = 0;

    int[] gameState ={2,2,2,2,2,2,2,2,2};

    int[][] winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropin(View view){



        ImageView counter = (ImageView) view;



        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter] == 2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            Log.i("Information", counter.getTag().toString());

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.xxx);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);

            for (int[] winningState : winningStates) {
                String message = " ";
                if (gameState[winningState[0]] == gameState[winningState[1]] && gameState[winningState[1]] == gameState[winningState[2]] && gameState[winningState[0]] != 2) {

                         gameActive = false;

                    if (activePlayer == 1) {
                        message = "X won the game";
                    } else {
                        message = "O won the game";
                    }
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

                    Button wButton = (Button) findViewById(R.id.button);

                    TextView winnerText = (TextView) findViewById(R.id.winnerText);

                    winnerText.setText(message);

                    winnerText.setVisibility(View.VISIBLE);

                    wButton.setVisibility(View.VISIBLE);


                }


            }
        }
    }

    public void playAgain(View view){
        Button button = (Button) findViewById(R.id.button);

        TextView winnerText = (TextView) findViewById(R.id.winnerText);

        winnerText.setVisibility(View.INVISIBLE);

        button.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i =0 ; i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int i=0 ; i<gameState.length;i++){
                gameState[i] = 2;
        }

        gameActive= true;

        activePlayer = 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
