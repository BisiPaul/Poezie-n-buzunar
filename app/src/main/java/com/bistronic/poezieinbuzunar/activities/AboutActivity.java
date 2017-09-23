package com.bistronic.poezieinbuzunar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setToolbar();
    }
    public void setToolbar(){
        TextView toolbar_title;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.about_activity_title));
    }
}
