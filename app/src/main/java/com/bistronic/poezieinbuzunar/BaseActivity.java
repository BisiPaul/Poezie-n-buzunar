package com.bistronic.poezieinbuzunar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.parse.Parse;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //Parse initialization
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("RrhJ75BLTkpVSH4XmmRddnkxQRwPZMIjHxXR3Oqv")
                .clientKey("vcPaHwWH4enAH5uQgtAaMzi03ICY40HjX1GaHmZa")
                .server("https://parseapi.back4app.com/").build()
        );

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
