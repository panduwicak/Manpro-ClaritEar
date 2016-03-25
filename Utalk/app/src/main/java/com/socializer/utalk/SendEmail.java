package com.socializer.utalk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by wicakcak on 11/30/2015.
 */
public class SendEmail extends Activity {

    EditText mailtext;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_email);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mailtext = (EditText) findViewById(R.id.emailbox);
                String message = mailtext.getText().toString();
                send(message);
            }
        });
    }

    protected void send(String message) {
        String[] to = new String[]{"utalk.contact.us@gmail.com"};
        String subject = ("Utalk User's Feedback");
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT,message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Send Email Via"));
    }

}
