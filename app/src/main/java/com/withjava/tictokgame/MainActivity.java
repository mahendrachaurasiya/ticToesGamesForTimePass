package com.withjava.tictokgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0=green , 1= cross
    int activplayer=0;
    boolean gameactive=true;
    // 2 means unplayed
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpostion={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2&&gameactive) {
            gamestate[tappedcounter]= activplayer;

            counter.setTranslationY(-1000f);
            if (activplayer == 0) {
                counter.setImageResource(R.drawable.green);
                activplayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activplayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int [] winning: winningpostion){
                if (gamestate[winning[0]]==gamestate[winning[1]]&&gamestate[winning[1]]==gamestate[winning[2]]
                &&gamestate[winning[0]]!=2) {

                    gameactive=false;
                    String winner=" cross";
                    if (gamestate[winning[0]]==0){
                        winner=" frog";

                    }
                    //some has won

                    TextView winnermessage=(TextView)findViewById(R.id.winnermessage);
                    winnermessage.setText( winner +" has win!!!");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                    layout.setVisibility(View.VISIBLE);
                }else{
                    boolean  gameover=true;
                    for (int countstate :gamestate){
                        if (countstate==2) gameover= false;
                    }
                    if (gameover){
                        TextView winnermessage=(TextView)findViewById(R.id.winnermessage);
                        winnermessage.setText( "it's draw");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                        layout.setVisibility(View.VISIBLE);

                    }
                }
                }
            }

    }
    public void playAgain(View view) {
          gameactive=true;
        LinearLayout layout=findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
        activplayer=0;
        for(int i=0; i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridlayout);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);


}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
