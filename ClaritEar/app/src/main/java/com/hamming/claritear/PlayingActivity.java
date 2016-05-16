package com.hamming.claritear;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Class untuk bermain dengan instrument guitar
 */
public class PlayingActivity extends AppCompatActivity {
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

    /**
     * untuk mendeclare seluruh komponen pada xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playing);
        score = (TextView) findViewById(R.id.tv_play_printscore);
        play = (Button) findViewById(R.id.btn_play_playChord);
        maj = (Button)findViewById(R.id.btn_maj);
        min = (Button)findViewById(R.id.btn_min);
        seventh = (Button)findViewById(R.id.btn_seventh);
        min7 = (Button)findViewById(R.id.btn_min7);
        maj7 = (Button)findViewById(R.id.btn_maj7);
        dim = (Button)findViewById(R.id.btn_dim);
        dim7 = (Button)findViewById(R.id.btn_dim7);
        aug = (Button)findViewById(R.id.btn_aug);
        sus2 = (Button)findViewById(R.id.btn_sus2);
        sus4 = (Button)findViewById(R.id.btn_sus4);

        setChordURLWhenTrue();
        resetScore();
        getHighScore();
    }

    /**
     * method untuk tombol playchord
     * menggunakan media plaer untuk memainkan sound yang diakses dengan link url
     * @param v
     */
    public void playChord(View v){
        try{
            MediaPlayer chordPlayer = new MediaPlayer();
            Uri theUri = Uri.parse(chordURL);
            chordPlayer.setDataSource(getApplicationContext(), theUri);
            chordPlayer.prepare();
            chordPlayer.start();
        } catch (Exception e){
            Toast.makeText(getBaseContext(),"Can't play chord", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * method untuk kembali ke home apabila ditekan back
     * namun dengan peringatan "yes" atau "no" karena apabila back maka progress session akan hilang
     */
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

    /**
     * method untuk kembali ke home
     */
    public void backHome(){
        Intent backHome = new Intent(PlayingActivity.this, HomeActivity.class);
        startActivity(backHome);
        resetScore();
    }

    /**
     * method untuk me-reset score menjadi 0
     * @return
     */
    public int resetScore(){
        return currentscore = 0;
    }

    /**
     * meng-set chord secara random dari database sesuai score saat ini
     * semakin tinggi score saat ini maka chord yang harus ditebak semakin sulit
     * @return
     */
    public String setChordURLWhenTrue(){
        if(currentscore <11){
            String urlChord = "http://teamhamming.esy.es/difficulty1.php";
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
            String urlChord = "http://teamhamming.esy.es/difficulty2.php";
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
            String urlChord = "http://teamhamming.esy.es/difficulty3.php";
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
            String urlChord = "http://teamhamming.esy.es/difficulty4.php";
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
            String urlChord = "http://teamhamming.esy.es/difficulty5.php";
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
            String urlChord = "http://teamhamming.esy.es/difficulty6.php";
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

    /**
     * dipanggil saat user menekan tombol major
     * @param view
     */
    public void clickMajor(View view){
        checkAnswer("maj");
    }

    /**
     * dipanggil saat user menekan tombol minor
     * @param view
     */
    public void clickMinor(View view){
        checkAnswer("min");
    }

    /**
     * dipanggil saat user menekan tombol seventh
     * @param view
     */
    public void clickSeventh(View view){
        checkAnswer("seventh");
    }

    /**
     * dipanggil saat user menekan tombol major7
     * @param view
     */
    public void clickMajor7(View view){
        checkAnswer("maj7");
    }

    /**
     * dipanggil saat user menekan tombol minor7
     * @param view
     */
    public void clickMinor7(View view){
        checkAnswer("min7");
    }

    public void clickSus2(View view){
        checkAnswer("sus2");
    }

    /**
     * dipanggil saat user menekan tombol sus4
     * @param view
     */
    public void clickSus4(View view){
        checkAnswer("sus4");
    }

    /**
     * dipanggil saat user menekan tombol diminished
     * @param view
     */
    public void clickDim(View view){
        checkAnswer("dim");
    }

    /**
     * dipanggil saat user menekan tombol diminished7
     * @param view
     */
    public void clickDim7(View view){
        checkAnswer("dim7");
    }

    /**
     * dipanggil saat user menekan tombol augmented
     * @param view
     */
    public void clickAug(View view){
        checkAnswer("aug");
    }

    /**
     * Dipanggil saat user menjawab jawaban yang benar dengan menampilkan pemberitahuan
     * dan menambah score saat ini +1
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

    /**
     * method check answer yg dipanggil setiap tombol-chord ditekan dengan parameter masing-masing
     * @param cat
     */
    public void checkAnswer(String cat){
        category = cat;
        HashMap postChordURL = new HashMap();
        postChordURL.put("chordURL", chordURL);
        postChordURL.put("category", category);

        String urlMatchAnswer = "http://teamhamming.esy.es/MatchAnswer.php";
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
                            Intent highscore = new Intent(PlayingActivity.this,HighscoreActivity.class);
                            startActivity(highscore);
                            finish();
                        }
                        else{
                            backHome();
                            finish();
                        }
                        break;

                    default:
                        Toast.makeText(PlayingActivity.this, "Error occurs", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
        matchAnswer.execute(urlMatchAnswer);
    }

    /**
     * untuk mengambil highscore dari database dari info logininfo dan username
     */
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

    /**
     * untuk mengupdate highscore username bersangkutan di database
     */
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

    /**
     * untuk mendapatkan jawaban yg benar dari jawaban user yang salah
     */
    public void getCorrectAnswer(){
        HashMap getAnswer = new HashMap();
        getAnswer.put("chordURL", chordURL);

        String urlGetAnswer = "http://teamhamming.esy.es/getAnswer.php";
        PostResponseAsyncTask answer = new PostResponseAsyncTask(this, getAnswer, false, new AsyncResponse() {
            @Override
            public void processFinish(String answer) {
                correctAns = answer;
            }
        });answer.execute(urlGetAnswer);
    }
}
