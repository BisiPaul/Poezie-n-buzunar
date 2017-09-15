package com.bistronic.poezieinbuzunar.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;

/**
 * Created by Larisa on 05.08.2017.
 */
public class ReadPoemActivity extends AppCompatActivity {
    private  int clicksFavorite=0;
    private TextView mTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_poem);

        //for the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String text = this.getIntent().getExtras().getString("text");

         mTextView = (TextView) findViewById(R.id.textViewPoem);
         mTextView.setMovementMethod(new ScrollingMovementMethod());
         mTextView.setText(text);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_readpoem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.unfavourite:
                clicksFavorite++;
                if(clicksFavorite%2==1)
                    item.setIcon(R.drawable.icon_favorite);
                else
                    item.setIcon(R.drawable.icon_unfavorite);

                return true;
            case android.R.id.home:
                finish(); //closing the activity , the remeining one being the listActivity
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
