package com.hamming.claritear;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;

/**
 * Kelas Login untuk activity Login
 */
public class Login extends AppCompatActivity {
    EditText inputUsername, inputPassword;
    Button login;

    /**
     * Method onCreate untuk mendeklarasi button, edittext, dan textview
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText)findViewById(R.id.et_login_username);
        inputPassword = (EditText)findViewById(R.id.et_login_password);
        login = (Button)findViewById(R.id.btn_login_login);
    }

    /**
     * Method login untuk login saat tombol login ditekan
     * @param v
     */
    public void login(View v){
        HashMap postDataLogin = new HashMap();
        postDataLogin.put("usernamelogin", inputUsername.getText().toString());
        postDataLogin.put("passlogin", inputPassword.getText().toString());

        if(inputUsername.getText().toString().equals("")||inputPassword.getText().toString().equals("")){
            Toast.makeText(this, "One or more field(s) are empty", Toast.LENGTH_LONG).show();
        }
        else{
            String urlLogin = "http://teamhamming.esy.es/Login.php";
            PostResponseAsyncTask loginTask = new PostResponseAsyncTask(this, postDataLogin, new AsyncResponse() {
                @Override
                public void processFinish(String result2) {
                    switch(result2){
                        case "true":
                            //Menyimpan info login berupa username dengan SharedPreferance
                            SharedPreferences sharedPrefLogin = getSharedPreferences("loginInfo", Context
                                    .MODE_PRIVATE);

                            SharedPreferences.Editor editor = sharedPrefLogin.edit();
                            editor.putString("username", inputUsername.getText().toString());
                            editor.apply();
                            Intent login = new Intent(Login.this,HomeActivity.class);
                            startActivity(login);
                            break;

                        case "false":
                            Toast.makeText(Login.this, "Invalid password or username", Toast.LENGTH_LONG)
                                    .show();
                            break;

                        default:
                            Toast.makeText(Login.this,"Connection error. Please try again", Toast.LENGTH_LONG)
                                    .show();
                            break;
                    }

                }
            });
            loginTask.execute(urlLogin);
        }
    }

    /**
     * Method toRegister untuk pindah ke activity register saat menekan text "Make new account"
     * @param v
     */
    public void toRegister(View v){
        Intent toRegister = new Intent(Login.this,Registration.class);
        startActivity(toRegister);
        finish();
    }

    /**
     * Method onBackPressed untuk keluar dari program saat tombol back ditekan
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory(Intent.CATEGORY_HOME);
        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exit);
    }
}
