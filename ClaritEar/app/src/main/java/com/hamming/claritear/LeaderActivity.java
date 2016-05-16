package com.hamming.claritear;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * kelas untuk menampilan leaderboard
 */
public class LeaderActivity extends Activity {

    /**
     * method untuk menampilan textview yang berisi leaderboard
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        String url = "http://teamhamming.esy.es/leader.php";
        WebView wvleader = (WebView)findViewById(R.id.wv_leaderboard);
        wvleader.loadUrl(url);
    }
}
