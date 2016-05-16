package com.hamming.claritear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Kelas HomeActivity
 */
public class HomeActivity extends AppCompatActivity {
    public static TextView detailusername, detailhighscore;
    Button start;

    /**
     * method override untuk mendeclare komponen pada xml ke java.
     * Juga dibutuhkan untuk menampilkan hal-hal yang perlu ditempilkan seperti informasi user dll
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        detailusername = (TextView)findViewById(R.id.tv_main_username);
        detailhighscore = (TextView)findViewById(R.id.tv_main_highscore);
        start = (Button)findViewById(R.id.btn_main_start);

        //Mengambil data username langsung dari loginInfo
        SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                .MODE_PRIVATE);
        String sharedUser = sharedPrefLogin.getString("username", "");
        detailusername.setText(sharedUser + "!");

        //Mengambil data dari database berdasarkan username
        HashMap postShared = new HashMap();
        postShared.put("username",sharedUser);

        //Mengambil data highscore dari database
        String urlhighscore = "http://teamhamming.esy.es/FetchHighscore.php";
        PostResponseAsyncTask fetchHighScore = new PostResponseAsyncTask(this, postShared, new AsyncResponse() {
            @Override
            public void processFinish(String result3) {
                detailhighscore.setText(result3);
            }
        });
        fetchHighScore.execute(urlhighscore);
    }

    /**
     * method startPlaying untuk mulai bermain jika tombol "start" ditekan
     */
    public void startPlaying(View v){
        Intent startPlay = new Intent(HomeActivity.this, ChooseInstrument.class);
        startActivity(startPlay);
        finish();
    }

    /**
     * method override onBackPressed untuk keluar saat tombol back pada hardware ditekan
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        appExit();
    }

    /**
     * method yang akan menampilkan menu dropdown pada pojok kanan atas layar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.home_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * method yang digunakan untuk menentukan apa yang dilakukan pada setiap opsi yang tertera
     * pada menu dropdown
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case (R.id.share):
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "I am now using ClaritEar to train my ear and musical ability\n\n" +
                        "Go get and download ClaritEar for free here www.teamhamming.esy.es");
                shareIntent.setType("text/plain");
                shareIntent.createChooser(shareIntent, "Share via");
                startActivity(shareIntent);
                break;
            case (R.id.leaderboard):
                Intent leaderIntent = new Intent(HomeActivity.this, LeaderActivity.class);
                startActivity(leaderIntent);
                break;
            case(R.id.about):
                Uri uriUrl = Uri.parse("http://www.teamhamming.esy.es");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
                break;
            case(R.id.logout):
                SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                        .MODE_PRIVATE);
                //Menghapus memory loginInfo shared preferences
                SharedPreferences.Editor editor = sharedPrefLogin.edit();
                editor.clear();
                editor.apply();

                //Kembali ke halaman Login
                Intent logout = new Intent(HomeActivity.this, Login.class);
                startActivity(logout);
                finish();
                break;

            case (R.id.exit):
                appExit();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * method appExit yang akan dipanggil saat ingin keluar dari app
     */
    public void appExit(){
        this.finish();
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
    }
}
