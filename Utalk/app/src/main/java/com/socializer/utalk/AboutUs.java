package com.socializer.utalk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by wicakcak on 11/30/2015.
 */
public class AboutUs extends Activity {
    WebView wv_katalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        wv_katalog = (WebView) findViewById(R.id.wv_aboutus);
        String url = "http://utalkapp.esy.es/aboutus.php";
        wv_katalog.loadUrl(url);
    }
}
