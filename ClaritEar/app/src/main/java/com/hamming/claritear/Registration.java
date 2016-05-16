package com.hamming.claritear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Registration class for registration activity
 */
public class Registration extends AppCompatActivity {
    EditText username, email, password, rptpassword;
    Button register;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent toLogin = new Intent(Registration.this,Login.class);
        startActivity(toLogin);
        finish();
    }

    /**
     * Method onCreate untuk mendeklarasi fungsi button dan editText di activity_registration.xml
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        username = (EditText)findViewById(R.id.et_regist_username);
        email = (EditText)findViewById(R.id.et_regist_email);
        password = (EditText)findViewById(R.id.et_regist_password);
        rptpassword = (EditText)findViewById(R.id.et_regist_repeatPassword);
        register = (Button)findViewById(R.id.btn_regist_register);
    }

    /**
     * Method register untuk menentukan apa yang akan dilakukan bila button register ditekan
     */
    public void register(View v){
        HashMap PostData = new HashMap();
        PostData.put("username",username.getText().toString());
        PostData.put("email",email.getText().toString());
        PostData.put("password",password.getText().toString());

        if(username.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals("") || rptpassword.getText().toString().equals("")){
            Toast.makeText(this,"One ore more field(s) are empty",Toast.LENGTH_LONG).show();
        }
        else if(!validateUser(username.getText().toString())){
            username.setError("Username must contain at least 4 characters");
            username.requestFocus();
        }
        else if(!validateEmail(email.getText().toString())){
            email.setError("Invalid Email Address");
            email.requestFocus();
        }
        else if(!validatePassword(password.getText().toString())){
            password.setError("Password must contain at least 6 characters");
            password.requestFocus();
        }
        else if(!password.getText().toString().equals(rptpassword.getText().toString())) {
            rptpassword.setError("Passwords don't match");
            rptpassword.requestFocus();
        }
        else{
            String urlRegister = "http://teamhamming.esy.es/Register.php";
            PostResponseAsyncTask registerTask = new PostResponseAsyncTask(this, PostData, new AsyncResponse() {
                @Override
                public void processFinish(String result) {
                    Toast.makeText(Registration.this,result,Toast.LENGTH_LONG).show();
                    if(result.equals("You are now registered")) {
                        Intent i = new Intent(Registration.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                    else {
                        Toast.makeText(Registration.this,"Connection error. Please try again!",Toast.LENGTH_LONG)
                                .show();
                    }
                }
            });
            registerTask.execute(urlRegister);
        }
    }

    /**
     * Method validateUser untuk memvalidasi input username
     * @param stringusr
     * @return
     */
    private boolean validateUser(String stringusr) {
        //username harus lebih dari 4 karakter
        if(stringusr.length()<4){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method validatePassword untuk memvalidasi input input password
     * @param stringpass
     * @return
     */
    private boolean validatePassword(String stringpass) {
        //password harus lebih dari 6 karakter
        if(stringpass.length()<6){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Method validateEmail untuk memvalidasi input email
     * @param stringemail
     * @return
     */
    private boolean validateEmail(String stringemail) {
        //Menggunakan Regular Expression
        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(stringemail);

        return matcher.matches();
    }
}
