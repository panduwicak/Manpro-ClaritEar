package com.hamming.claritear;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class PlayingActivity extends AppCompatActivity {
    TextView score, answer;
    Button play, cancel, cont;
    public String chordURL;
    public int currentscore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        score = (TextView) findViewById(R.id.tv_play_printscore);
        answer = (TextView) findViewById(R.id.tv_play_answer);
        play = (Button) findViewById(R.id.btn_play_playChord);
        cancel = (Button) findViewById(R.id.btn_play_cancel);
        cont = (Button) findViewById(R.id.btn_play_continue);
    }

    public void playChord(View v){
        this.setChordURL();

        try{
            MediaPlayer chordPlayer = new MediaPlayer();
            Uri theUri = Uri.parse(chordURL);
            chordPlayer.setDataSource(getApplicationContext(), theUri);
            chordPlayer.prepare();
            chordPlayer.start();
        } catch (Exception e){
            Toast.makeText(getBaseContext(),"Sorry something is wrong", Toast.LENGTH_LONG).show();
        }

        answer.setText("");
        currentscore = currentscore + 1;
        score.setText(Integer.toString(currentscore));
    }

    public void nextQuest(View v){
        this.resetScore();
        score.setText(Integer.toString(currentscore));
        answer.setText("You've pressed continue");
    }

    public void cancel(View v){
        Intent backHome = new Intent(PlayingActivity.this, HomeActivity.class);
        startActivity(backHome);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backHome = new Intent(PlayingActivity.this, HomeActivity.class);
        startActivity(backHome);
        finish();
    }

    public void resetScore(){
        currentscore = 0;
    }

    public String setChordURL(){
        chordURL = "http://teamhamming.esy.es/songsample/chord.mp3";
        return chordURL;
    }
}
