package com.socializer.utalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashh);

        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(4000);
                    Intent start = new Intent(Splash.this, MainActivity.class);
                    startActivity(start);
                    finish();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
}
