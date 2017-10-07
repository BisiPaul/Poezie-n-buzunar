package com.bistronic.poezieinbuzunar.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textQuote);
        final Typeface font = Typeface.createFromAsset(getAssets(), "fonts/expresswayrg.ttf");

        textView.setTypeface(font);

        ParseUser user = ParseUser.getCurrentUser();
        // Determine whether the current user is an anonymous user
        if (user != null && ParseAnonymousUtils.isLinked(user)) {
            Intent mainIntent = new Intent(MainActivity.this , MainActivity.class);
            MainActivity.this.startActivity(mainIntent);
            MainActivity.this.finish();
        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            if (user != null) {
                Intent listIntent = new Intent(MainActivity.this , ListActivity.class);
                MainActivity.this.startActivity(listIntent);
                MainActivity.this.finish();
            }
        }
        setToolbar();
    }

    public void setToolbar(){
        TextView toolbar_title;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.app_name));

        if(!isNetworkAvailable()){
            setContentView(R.layout.no_internet);
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showSignInActivity(View view){
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
    }

    public void showRegisterActivity(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public  void refreshActivity(View v){
        if(isNetworkAvailable()){
            findViewById(R.id.no_internet).setVisibility(View.GONE);
            startActivity(getIntent());
            this.finish();

        }

    }

}
