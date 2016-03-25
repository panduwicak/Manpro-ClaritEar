package com.socializer.utalk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by wicakcak on 12/13/2015.
 */
public class WhatThisMain extends FragmentActivity {
    ViewPager whatthisapp;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.whatsthismain);
        whatthisapp = (ViewPager) findViewById(R.id.swipepager);
        PagerAdapter whatthisadapter = new WhatThisAdapter(getSupportFragmentManager());
        whatthisapp.setAdapter(whatthisadapter);
    }

    public void toMain(View v){
        Intent tomain = new Intent(this,MainActivity.class);
        startActivity(tomain);
        finish();
    }
}
