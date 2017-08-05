package com.bistronic.poezieinbuzunar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LogInActivity extends BaseActivity {
    EditText usernameEditText;
    EditText passwordEditText;

    Button loginButton;
    Button forgotPasswordButton;

    TextView usernameTextView;
    TextView passwordTextView;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        loginButton = (Button) findViewById(R.id.loginButton);
        forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        usernameTextView = (TextView) findViewById(R.id.usernameTextView);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
        progressDialog = new ProgressDialog(LogInActivity.this);

        getKeyHash();

        //Maybe Parse Initiaise will be needed here

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Logging in");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            parseLogin();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Please Wait");
                progressDialog.setTitle("Registering");
                progressDialog.show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //parseForgotPassword();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        Parse.setLogLevel(Parse.LOG_LEVEL_VERBOSE);
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "408752285858");
        installation.saveInBackground();

    }

    void parseLogin(){
        ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (parseUser != null) {
                    progressDialog.dismiss();
                    getUserDetailFromParse();
                } else {
                    progressDialog.dismiss();
                    alertDisplayer("Login Fail", e.getMessage()+" Please re-try");
                }
            }
        });
    }

    void alertDisplayer(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this)
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

    void getUserDetailFromParse(){
        ParseUser user = ParseUser.getCurrentUser();
        usernameTextView.setText(user.getUsername());
        alertDisplayer("Welcome", " " + usernameTextView.getText().toString());
    }



}
