package com.bistronic.poezieinbuzunar.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.bistronic.poezieinbuzunar.R;
import com.parse.Parse;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3 *1000; // the length is in miliseconds

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.activity_splash);

//        Parse.enableLocalDatastore(getApplicationContext());
        //Parse initialization
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("RrhJ75BLTkpVSH4XmmRddnkxQRwPZMIjHxXR3Oqv")
                .clientKey("vcPaHwWH4enAH5uQgtAaMzi03ICY40HjX1GaHmZa")
                .server("https://parseapi.back4app.com/").build()
        );

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the MainActivity. */
                Intent mainIntent = new Intent(SplashActivity.this , MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }



}