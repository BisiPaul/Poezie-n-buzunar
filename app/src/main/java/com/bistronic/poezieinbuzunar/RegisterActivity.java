package com.bistronic.poezieinbuzunar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Looper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {
    EditText usernameEditTextRegister;
    EditText passwordEditTextRegister;
    EditText confirmPasswordEditTextRegister;

    Button registerButton;

    //TextView usernameTextViewRegister;
    //TextView passwordTextViewRegister;
    //TextView confirmPasswordTextViewRegister;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditTextRegister = (EditText) findViewById(R.id.usernameEditTextRegister);
        passwordEditTextRegister = (EditText) findViewById(R.id.passwordEditTextRegister);
        confirmPasswordEditTextRegister = (EditText) findViewById(R.id.confirmPasswordEditTextRegister);

        registerButton = (Button) findViewById(R.id.registerButton);

        //usernameTextViewRegister = (TextView) findViewById(R.id.usernameTextViewRegister);
        //passwordTextViewRegister = (TextView) findViewById(R.id.passwordTextViewRegister);
        //confirmPasswordTextViewRegister = (TextView) findViewById(R.id.confirmPasswordTextViewRegister);

        progressDialog = new ProgressDialog(RegisterActivity.this);


        getKeyHash();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Registering");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseRegister();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

    }

    void parseRegister() {
        ParseUser user = new ParseUser();
        user.setUsername(usernameEditTextRegister.getText().toString());
        Looper.prepare();

        String password = passwordEditTextRegister.getText().toString();
        String confirmedPassword = confirmPasswordEditTextRegister.getText().toString();


        if( checkPasswordMatching( password , confirmedPassword ) && checkPasswordLength( password )) {
            user.setPassword(passwordEditTextRegister.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        progressDialog.dismiss();
                        //.setText(ParseUser.getCurrentUser().getUsername());
                        saveNewUser();
                    } else {
                        progressDialog.dismiss();
                        alertDisplayer("Register Fail", e.getMessage());
                    }
                }
            });
        } else {
            alertDisplayer("Register failed" ,"The passwords do not match.");
            Log.d("test","innnnnnnnnnnnnnnnnnnnnnnnnnnn");
            progressDialog.dismiss();

        }
    }



    private void getKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.bistronic.poezieinbuzunar", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            //something
        } catch (NoSuchAlgorithmException e) {
            //something
        }
    }


    void saveNewUser(){
        ParseUser user = ParseUser.getCurrentUser();
        user.setUsername(usernameEditTextRegister.getText().toString());
        user.setPassword(passwordEditTextRegister.getText().toString());
        user.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                alertDisplayer("Register succesful!", "Welcome, " + usernameEditTextRegister.getText().toString() + "!");
            }
        });
    }


    void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

    boolean checkPasswordLength(String password){
        if(password.length() < 3)
            return false;
        else
            return true;
    }

    boolean checkPasswordMatching(String pass, String confirm){
        if(pass.equals(confirm))
            return true;
        else
            return false;
    }

}
