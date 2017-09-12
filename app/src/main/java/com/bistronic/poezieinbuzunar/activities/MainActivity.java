package com.bistronic.poezieinbuzunar.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;
import com.bistronic.poezieinbuzunar.activities.BaseActivity;
import com.bistronic.poezieinbuzunar.activities.LogInActivity;
import com.bistronic.poezieinbuzunar.activities.RegisterActivity;
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

}
