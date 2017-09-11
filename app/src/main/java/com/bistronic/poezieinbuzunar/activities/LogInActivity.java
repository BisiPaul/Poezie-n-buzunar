package com.bistronic.poezieinbuzunar.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bistronic.poezieinbuzunar.R;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInActivity extends BaseActivity {
    @Bind(R.id.usernameEditTextLogIn)
    EditText usernameEditText;

    @Bind(R.id.passwordEditTextLogIn)
    EditText passwordEditText;

    EditText forgotPasswordUsernameEditText;
    EditText forgotPasswordPasswordEditText1;
    EditText forgotPasswordConfirmPasswordEditText;

    @Bind(R.id.loginButton)
    Button loginButton;

    @Bind(R.id.forgotPasswordButton)
    Button forgotPasswordButton;

    ProgressDialog progressDialog;

    boolean successfulPasswordResetFlag = false;

    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(LogInActivity.this);

        //reinitialising the PasswordResetFlag
        successfulPasswordResetFlag = false;

        //General context for all the others activities
        Context mcontext = getApplicationContext();

        //Forgot password dialog view is inflated
        LayoutInflater inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View forgotPasswordDialogView = inflater.inflate(R.layout.dialog_forgot_password, null);
        forgotPasswordUsernameEditText = (EditText) forgotPasswordDialogView.findViewById(R.id.forgotPasswordUsernameEditText);
        forgotPasswordPasswordEditText1 = (EditText) forgotPasswordDialogView.findViewById(R.id.forgotPasswordPasswordEditText);
        forgotPasswordConfirmPasswordEditText = (EditText) forgotPasswordDialogView.findViewById(R.id.forgotPasswordConfirmEditText);

        // Create and inflate the Forget Password Dialog
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LogInActivity.this);
        alertDialogBuilder.setView(forgotPasswordDialogView);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    parseForgotPassword();
                                    successfulPasswordResetFlag = true;
                                } catch (Exception e) {
                                    successfulPasswordResetFlag = false;
                                    e.printStackTrace();
                                }

                                if (successfulPasswordResetFlag == true) {
                                    Toast.makeText(LogInActivity.this, "Password successfully reset", Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                } else
                                    Toast.makeText(LogInActivity.this, "Incorrect details, please try again", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // Instantiate the Alert Dialog
        alertDialog = alertDialogBuilder.create();

        getKeyHash();


    }

    @OnClick(R.id.loginButton)
    public void login () {
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

    @OnClick(R.id.forgotPasswordButton)
    public void forgotPassword() {


        // Show the Alert Dialog
        alertDialog.show();

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

                    //populate the ReadPoemActivity with poems
                    Intent listIntent = new Intent(LogInActivity.this, ListActivity.class);
                    startActivity(listIntent);
                    //LogInActivity.this.finish();
                } else {
                    progressDialog.dismiss();
                    alertDisplayer("Login Fail", e.getMessage()+" Please re-try");
                }
            }
        });
    }

    void parseForgotPassword(){
        //Looper.prepare();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", forgotPasswordUsernameEditText.getText().toString());
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    if( forgotPasswordPasswordEditText1.getText().toString().equals(forgotPasswordConfirmPasswordEditText.getText().toString()) ) {
                        objects.get(0).setPassword(forgotPasswordPasswordEditText1.getText().toString());
                        objects.get(0).saveInBackground();
                    }
                } else {
                    e.printStackTrace();
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
        alertDisplayer("Welcome", " " + usernameEditText.getText().toString());
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
