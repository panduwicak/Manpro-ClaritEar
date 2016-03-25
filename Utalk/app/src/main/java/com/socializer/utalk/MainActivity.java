package com.socializer.utalk;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

public class MainActivity extends AppCompatActivity implements AsyncResponse {
    Spinner spinner_situation;
    ArrayAdapter<CharSequence> adapter_situation;
    TextView question;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner_situation=(Spinner)findViewById(R.id.spinner_situation);
        adapter_situation=ArrayAdapter.createFromResource(this,R.array.situation,android.R.layout.simple_spinner_item);
        adapter_situation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_situation.setAdapter(adapter_situation);

        question = (TextView) findViewById(R.id.question);

        Boolean FirstRun = getSharedPreferences("preference",MODE_PRIVATE)
                .getBoolean("firstrun",true);

        if(FirstRun){
            WelcomeAlert();

            getSharedPreferences("preference",MODE_PRIVATE).edit().putBoolean("firstrun",false)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit_id:
                ShowAlert();
                break;
            case R.id.contact_id:
                SendEmail();
                break;
            case R.id.about_id:
                Aboutus();
                break;
            case R.id.help_id:
                Helphelp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        ShowAlert();
    }

    public void WelcomeAlert(){
        AlertDialog.Builder exitalert = new AlertDialog.Builder(this);
        exitalert.setMessage("Do you want to take a tour?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Helphelp();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Welcome!")
                .create();
        exitalert.show();
    }

    public void ShowAlert() {
        AlertDialog.Builder exitalert = new AlertDialog.Builder(this);
        exitalert.setMessage("Are you sure want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppExit();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        exitalert.show();
    }

    public void AppExit(){
        this.finish();
        Toast.makeText(this,"Exiting...",Toast.LENGTH_SHORT).show();
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
    }

    public void SendEmail(){
        Intent email = new Intent(this, SendEmail.class);
        this.startActivity(email);
    }

    public void Aboutus(){
        Intent about = new Intent(this, AboutUs.class);
        this.startActivity(about);
    }

    public void Helphelp(){
        Intent help = new Intent(this, WhatThisMain.class);
        this.startActivity(help);
    }

    public void generate(View v){
        if(spinner_situation.getSelectedItem().toString().equals("Your first date")){
            PostResponseAsyncTask firstdate = new PostResponseAsyncTask(this);
            firstdate.execute("http://utalkapp.esy.es/utalk/firstdate.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Approaching a girl")){
            PostResponseAsyncTask approachgirl = new PostResponseAsyncTask(this);
            approachgirl.execute("http://utalkapp.esy.es/utalk/approachgirl.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Bump with your ex")){
            PostResponseAsyncTask ex = new PostResponseAsyncTask(this);
            ex.execute("http://utalkapp.esy.es/utalk/ex.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Meeting the forgotten old friend")){
            PostResponseAsyncTask forgottenfriend = new PostResponseAsyncTask(this);
            forgottenfriend.execute("http://utalkapp.esy.es/utalk/forgottenfriend.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Meeting your girlfriend's parents")){
            PostResponseAsyncTask girlparent = new PostResponseAsyncTask(this);
            girlparent.execute("http://utalkapp.esy.es/utalk/girlparent.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Riding a Gojek")){
            PostResponseAsyncTask gojek = new PostResponseAsyncTask(this);
            gojek.execute("http://utalkapp.esy.es/utalk/gojek.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Old friend reunion")){
            PostResponseAsyncTask oldfriendreunion = new PostResponseAsyncTask(this);
            oldfriendreunion.execute("http://utalkapp.esy.es/utalk/oldfriendreunion.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Toilet chat")){
            PostResponseAsyncTask toilet = new PostResponseAsyncTask(this);
            toilet.execute("http://utalkapp.esy.es/utalk/toilet.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("Family gathering")){
            PostResponseAsyncTask family = new PostResponseAsyncTask(this);
            family.execute("http://utalkapp.esy.es/utalk/family.php");
        }
        else if (spinner_situation.getSelectedItem().toString().equals("(Choose a situation)")){
            Toast.makeText(this, "Please choose a situation", Toast.LENGTH_LONG).show();
        }
        else Toast.makeText(this, "Question is not yet available",
                    Toast.LENGTH_LONG).show();
    }

    @Override
    public void processFinish(String ask) {
        question.setText(ask);
    }
}
