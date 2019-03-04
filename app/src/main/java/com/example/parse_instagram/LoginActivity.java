package com.example.parse_instagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import xdroid.toaster.Toaster;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    // pulling out references to UI elements on login screen
    private EditText etUsername;
    private EditText etPassword;
    //to make EditTexts single line, use xml attributes
    //android:inputType="text"
    //android:maxLines="1"

    private Button btnLogin;
    private Button btnNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnNewAccount = findViewById(R.id.btnNewAccount);

        // setting onClickListener for Login button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieving contents of EditTexts and passing to login method
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username,password);
            }
        });

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //retrieving contents of EditTexts and passing to login method
                goSignupActivity();
            }
        });
        }
    private void goSignupActivity(){
        Intent j = new Intent(this, SignupActivity.class);
        startActivity(j);
        finish();

    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                // if there are no problems with the login process e will be null,
                // but otherwise an exception must be made to notify user/dev of
                // the problem
                if(e != null){
                    //added android:usesCleartextTraffic="true" to android manifest
                    //to fix login error
                    Toaster.toast("Invalid Login Credentials");

                    e.printStackTrace();
                    return;
            }
                // TODO: navigate to new activity if the user has signed in properly
               goMainActivity();
            }
    });
    }
        private void goMainActivity(){
        // specifying intent to start new activity
        Intent i = new Intent(this, MainActivity.class);
        // calling method to start new activity (main)
            startActivity(i);
        // calling finish method to end login activity
            finish();

        }
}
