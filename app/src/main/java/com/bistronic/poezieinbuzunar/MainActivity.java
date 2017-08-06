package com.bistronic.poezieinbuzunar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.parse.ParseUser;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Avoid Parse invalid session token error
        ParseUser.getCurrentUser().logOut();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }



}
