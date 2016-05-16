package com.hamming.claritear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Class untuk menampilkan highscore baru yang diraih user
 */
public class HighscoreActivity extends AppCompatActivity {
    TextView newhighscore;
    Button share;
    Button noshare;
    private String highscore;
    private String sharedUser;

    /**
     * method override yang akan menampilkan hal-hal yang dibutuhkan dan mendeclare componen pada
     * file xml ke file java
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        newhighscore = (TextView) findViewById(R.id.tv_newhighscore);

        //Mengambil data username langsung dari loginInfo
        SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                .MODE_PRIVATE);
        sharedUser = sharedPrefLogin.getString("username", "");

        //Mengambil data dari database berdasarkan username
        HashMap postShared = new HashMap();
        postShared.put("username",sharedUser);

        //Mengambil data highscore dari database
        String urlhighscore = "http://teamhamming.esy.es/FetchHighscore.php";
        PostResponseAsyncTask fetchHighScore = new PostResponseAsyncTask(this, postShared, false, new AsyncResponse() {
            @Override
            public void processFinish(String result3) {
                newhighscore.setText(result3);
                highscore = result3;
            }
        });
        fetchHighScore.execute(urlhighscore);
        share = (Button) findViewById(R.id.btn_share);
        noshare = (Button) findViewById(R.id.btn_noshare);
    }

    /**
     * method yang dipanggil apabila tombol share ditekan yang mengirim user ke tab share melalui
     * media sosial
     * @param v
     */
    public void clickShare(View v){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT,sharedUser + " has achieved new highscore of " + highscore
                + " point(s) in ClaritEar.\n\n" +
                "Go download ClaritEar and train your ear here www.teamhamming.esy.es");
        shareIntent.setType("text/plain");
        shareIntent.createChooser(shareIntent, "Share via");
        startActivity(shareIntent);
    }

    /**
     * dipanggil apabila user tidak ingin meng-share yang akan mengirim ke activity home
     * @param v
     */
    public void clickNoShare(View v){
        Intent backHome = new Intent(HighscoreActivity.this, HomeActivity.class);
        startActivity(backHome);
        finish();
    }

    /**
     * override yang akan mengirim user ke home apabila tombol back pada handphone ditekan
     */
    @Override
    public void onBackPressed() {
        Intent toHome = new Intent(HighscoreActivity.this, HomeActivity.class);
        startActivity(toHome);
        finish();
    }
}
