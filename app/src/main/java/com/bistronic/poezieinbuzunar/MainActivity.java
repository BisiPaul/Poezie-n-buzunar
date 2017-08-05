package com.bistronic.poezieinbuzunar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parse initialization
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("RrhJ75BLTkpVSH4XmmRddnkxQRwPZMIjHxXR3Oqv")
                .clientKey("vcPaHwWH4enAH5uQgtAaMzi03ICY40HjX1GaHmZa")
                .server("https://parseapi.back4app.com/").build()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_base, menu);
        return true;
    }


}
