package com.hamming.claritear;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

public class PlayingPianoActivity extends AppCompatActivity {

    TextView score;
    Button play;
    Button maj;
    Button min;
    Button seventh;
    Button min7;
    Button maj7;
    Button dim;
    Button dim7;
    Button aug;
    Button sus2;
    Button sus4;
    private String correctAns;
    public String chordURL;
    public String category;
    public int currentscore;
    public String highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing_piano);
        score = (TextView) findViewById(R.id.tv_piano_printscore);
        play = (Button) findViewById(R.id.btn_piano_playChord);
        maj = (Button)findViewById(R.id.btn_piano_maj);
        min = (Button)findViewById(R.id.btn_piano_min);
        seventh = (Button)findViewById(R.id.btn_piano_seventh);
        min7 = (Button)findViewById(R.id.btn_piano_min7);
        maj7 = (Button)findViewById(R.id.btn_piano_maj7);
        dim = (Button)findViewById(R.id.btn_piano_dim);
        dim7 = (Button)findViewById(R.id.btn_piano_dim7);
        aug = (Button)findViewById(R.id.btn_piano_aug);
        sus2 = (Button)findViewById(R.id.btn_piano_sus2);
        sus4 = (Button)findViewById(R.id.btn_piano_sus4);

        setChordURLWhenTrue();
        resetScore();
        getHighScore();
    }

    public void playChord(View v){
        try{
            MediaPlayer chordPlayer = new MediaPlayer();
            Uri theUri = Uri.parse(chordURL);
            chordPlayer.setDataSource(getApplicationContext(), theUri);
            chordPlayer.prepare();
            chordPlayer.start();
        } catch (Exception e){
            Toast.makeText(getBaseContext(), "Can't play chord", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder backalert = new AlertDialog.Builder(this);
        backalert.setTitle("Quit Session");
        backalert.setMessage("Are you sure want to quit and lose all your progresses?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        backHome();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        backalert.show();
    }

    public void backHome(){
        Intent backHome = new Intent(PlayingPianoActivity.this, HomeActivity.class);
        startActivity(backHome);
        resetScore();
    }

    public int resetScore(){
        return currentscore = 0;
    }

    public String setChordURLWhenTrue(){
        if(currentscore <11){
            String urlChord = "http://teamhamming.esy.es/pianodifficulty1.php";
            PostResponseAsyncTask fetchChordURL = new PostResponseAsyncTask(this, false, new AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    chordURL = url;
                    getCorrectAnswer();
                }
            });
            fetchChordURL.execute(urlChord);
            return chordURL;
        }
        else if(currentscore >= 11 && currentscore < 21){
            String urlChord = "http://teamhamming.esy.es/pianodifficulty2.php";
            PostResponseAsyncTask fetchChordURL = new PostResponseAsyncTask(this, false, new AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    chordURL = url;
                    getCorrectAnswer();
                }
            });
            fetchChordURL.execute(urlChord);
            return chordURL;
        }
        else if(currentscore >= 21 && currentscore < 31 ){
            String urlChord = "http://teamhamming.esy.es/pianodifficulty3.php";
            PostResponseAsyncTask fetchChordURL = new PostResponseAsyncTask(this, false, new AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    chordURL = url;
                    getCorrectAnswer();
                }
            });
            fetchChordURL.execute(urlChord);
            return chordURL;
        }
        else if(currentscore >= 31 && currentscore < 41 ){
            String urlChord = "http://teamhamming.esy.es/pianodifficulty4.php";
            PostResponseAsyncTask fetchChordURL = new PostResponseAsyncTask(this, false, new AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    chordURL = url;
                    getCorrectAnswer();
                }
            });
            fetchChordURL.execute(urlChord);
            return chordURL;
        }
        else if(currentscore >= 41 && currentscore < 46 ){
            String urlChord = "http://teamhamming.esy.es/pianodifficulty5.php";
            PostResponseAsyncTask fetchChordURL = new PostResponseAsyncTask(this, false, new AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    chordURL = url;
                    getCorrectAnswer();
                }
            });
            fetchChordURL.execute(urlChord);
            return chordURL;
        }
        else{//currentscore >=46
            String urlChord = "http://teamhamming.esy.es/pianodifficulty6.php";
            PostResponseAsyncTask fetchChordURL = new PostResponseAsyncTask(this, false, new AsyncResponse() {
                @Override
                public void processFinish(String url) {
                    chordURL = url;
                    getCorrectAnswer();
                }
            });
            fetchChordURL.execute(urlChord);
            return chordURL;
        }
    }

    public void clickMajor(View view){
        checkAnswer("maj");
    }

    public void clickMinor(View view){
        checkAnswer("min");
    }

    public void clickSeventh(View view){
        checkAnswer("seventh");
    }

    public void clickMajor7(View view){
        checkAnswer("maj7");
    }

    public void clickMinor7(View view){
        checkAnswer("min7");
    }

    public void clickSus2(View view){
        checkAnswer("sus2");
    }

    public void clickSus4(View view){
        checkAnswer("sus4");
    }

    public void clickDim(View view){
        checkAnswer("dim");
    }

    public void clickDim7(View view){
        checkAnswer("dim7");
    }

    public void clickAug(View view){
        checkAnswer("aug");
    }

    /**
     * Dipanggil saat user menjawab jawaban yang benar
     */
    public void trueAnswer(){
        LayoutInflater inflaterTrue = getLayoutInflater();
        View layoutTrue = inflaterTrue.inflate(R.layout.layout_toasttrue, (ViewGroup)
                findViewById(R.id.toast_true_root));

        Toast toastTrue = new Toast(getApplicationContext());
        toastTrue.setGravity(Gravity.CENTER|Gravity.TOP,0,250);
        toastTrue.setView(layoutTrue);
        toastTrue.setDuration(Toast.LENGTH_SHORT);
        toastTrue.show();

        currentscore = currentscore + 1;
        score.setText(Integer.toString(currentscore));
        setChordURLWhenTrue();
    }

    public void checkAnswer(String cat){
        category = cat;
        HashMap postChordURL = new HashMap();
        postChordURL.put("chordURL", chordURL);
        postChordURL.put("category", category);

        String urlMatchAnswer = "http://teamhamming.esy.es/PianoMatchAnswer.php";
        PostResponseAsyncTask matchAnswer = new PostResponseAsyncTask(this, postChordURL, false, new AsyncResponse() {
            @Override
            public void processFinish(String result) {
                switch (result){
                    case "true":
                        trueAnswer();
                        break;

                    case "false":
                        LayoutInflater inflaterFalse = getLayoutInflater();
                        View layoutFalse = inflaterFalse.inflate(R.layout.layout_toastfalse, (ViewGroup)
                                findViewById(R.id.toast_false_root));

                        TextView correctans = (TextView)layoutFalse.findViewById(R.id.correctanswer);
                        correctans.setText(correctAns);

                        Toast toastFalse = new Toast(getApplicationContext());
                        toastFalse.setDuration(Toast.LENGTH_SHORT);
                        toastFalse.setView(layoutFalse);
                        toastFalse.setGravity(Gravity.CENTER | Gravity.TOP, 0, 250);
                        toastFalse.show();

                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        v.vibrate(110);

                        if(currentscore > Integer.valueOf(highscore)){
                            updateHighScore();
                            Intent highscore = new Intent(PlayingPianoActivity.this,HighscoreActivity.class);
                            startActivity(highscore);
                        }
                        else{
                            backHome();
                        }
                        finish();
                        break;

                    default:
                        Toast.makeText(PlayingPianoActivity.this, "Error occurs", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        matchAnswer.execute(urlMatchAnswer);
    }

    public void getHighScore(){
        SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                .MODE_PRIVATE);
        String sharedUsername = sharedPrefLogin.getString("username", "");

        HashMap postShared = new HashMap();
        postShared.put("username",sharedUsername);

        String urlhighscore = "http://teamhamming.esy.es/FetchHighscore.php";
        PostResponseAsyncTask getHighScore = new PostResponseAsyncTask(this, postShared, new AsyncResponse() {
            @Override
            public void processFinish(String score) {
                highscore = score;
            }
        });
        getHighScore.execute(urlhighscore);
    }

    public void updateHighScore(){
        SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                .MODE_PRIVATE);
        String username = sharedPrefLogin.getString("username", "");

        HashMap updateScore = new HashMap();
        updateScore.put("score", String.valueOf(currentscore));
        updateScore.put("username", username);

        String urlupdate = "http://teamhamming.esy.es/UpdateHighScore.php";
        PostResponseAsyncTask updateHighScore = new PostResponseAsyncTask(this, updateScore, new AsyncResponse() {
            @Override
            public void processFinish(String s) {

            }
        });
        updateHighScore.execute(urlupdate);
    }

    public void getCorrectAnswer(){
        HashMap getAnswer = new HashMap();
        getAnswer.put("chordURL", chordURL);

        String urlGetAnswer = "http://teamhamming.esy.es/PianoGetAnswer.php";
        PostResponseAsyncTask answer = new PostResponseAsyncTask(this, getAnswer, false, new AsyncResponse() {
            @Override
            public void processFinish(String answer) {
                correctAns = answer;
            }
        });answer.execute(urlGetAnswer);
    }
}
