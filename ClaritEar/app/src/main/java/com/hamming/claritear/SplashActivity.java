package com.hamming.claritear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Class untuk splashscreen
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * method override untuk mendeclare component pada xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //menggunakan thread untuk mengatur sleep dan lainnya
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(4500);

                    //Pengecekean untuk auto login
                    SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                            .MODE_PRIVATE);
                    //Jika memori masih menyimpan string data login maka dari splashscreen akan langsung ke home
                    if (sharedPrefLogin.getString("username", "").length() == 0) {
                        startActivity(new Intent(SplashActivity.this, Login.class));
                    }
                    //Jika tidak maka dari splashscreen akan ke login page
                    else {
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}
