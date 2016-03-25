package com.socializer.utalk;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

/**
 * Created by wicakcak on 12/1/2015.
 */
public class Help extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        TextView help = (TextView) findViewById(R.id.helphelp);
        help.setText(Html.fromHtml("<html>\n" +
                "<body style=\"background-color:LightCyan\">\n" +
                "\n" +
                "<h1 \n" +
                "style=\"text-align:center;color:Black;font-family:Trebuchet MS\">\n" +
                "Help\n" +
                "</h1>\n" +
                "<p style=\"color:black;font-family:Trebuchet MS\">UTALK Mobile Application is an app to provide user with sample question to start conversation in awkward situation.</p>\n" +
                "<p style=\"color:black;font-family:Trebuchet MS\">UTALK Mobile Application used database to generate user input from the application. To get different sample question, user need to click the generate button couple of times until the user satisfied with the question.</p>\n" +
                "\n" +
                "<p style=\"color:black;font-family:Trebuchet MS\">UTALK Mobile Application can be used from android version 1.6 \"Donut\" to the latest version of android, version 5.0 \"Lollipop\". </p>\n" +
                "\n" +
                "<p style=\"color:black;font-family:Trebuchet MS\">For further information, contact us by clicking on \"Contact Us\" button on the top of the application layout and send us your feedbacks or comment. </p>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>"));
    }
}
