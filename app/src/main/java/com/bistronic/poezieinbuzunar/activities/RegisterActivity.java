package com.bistronic.poezieinbuzunar.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    @Bind(R.id.usernameEditTextRegister)
    EditText usernameEditTextRegister;

    @Bind(R.id.passwordEditTextRegister)
    EditText passwordEditTextRegister;

    @Bind(R.id.confirmPasswordEditTextRegister)
    EditText confirmPasswordEditTextRegister;

    @Bind(R.id.registerButton)
    Button registerButton;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(RegisterActivity.this);

        getKeyHash();

        setToolbar();
    }

    public void setToolbar(){
        TextView toolbar_title;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.app_name));
    }

    @OnClick(R.id.registerButton)
    void parseRegister() {
        ParseUser user = new ParseUser();
        user.setUsername(usernameEditTextRegister.getText().toString());

        String password = passwordEditTextRegister.getText().toString();
        String confirmedPassword = confirmPasswordEditTextRegister.getText().toString();

        if( !checkPasswordMatching( password, confirmedPassword ) ) {
            alertDisplayer("Register Fail" ,"The passwords do not match.");
            progressDialog.dismiss();
        } else if( !checkPasswordLength( password ) ) {
            alertDisplayer("Register Fail" ,"The password must have at least 3 characters.");
            progressDialog.dismiss();
        } else {
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
                alertDisplayerSucces("Register succesful!", "Welcome, " + usernameEditTextRegister.getText().toString() + "!");
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

    void alertDisplayerSucces(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        Intent listIntent = new Intent(RegisterActivity.this, ListActivity.class);
                        startActivity(listIntent);
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
