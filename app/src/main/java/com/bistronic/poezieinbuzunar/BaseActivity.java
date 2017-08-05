package com.bistronic.poezieinbuzunar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.read_poems:
                //here method
                return true;
            case R.id.about:
                // here method
                return true;
            case R.id.exit:
                showExit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void showExit(){
        System.exit(0);
    }



}
