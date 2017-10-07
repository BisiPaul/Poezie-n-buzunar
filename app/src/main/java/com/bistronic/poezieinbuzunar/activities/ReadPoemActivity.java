package com.bistronic.poezieinbuzunar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;

/**
 * Created by Larisa on 05.08.2017.
 */
public class ReadPoemActivity extends AppCompatActivity {
    private  int clicksFavorite = 0;
    private TextView mTextView;

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.setIntent(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(this.getIntent().getExtras());
        setContentView(R.layout.activity_read_poem);
        String text = "Try again",title="",author="";

        if( this.getIntent().getExtras() != null) {
            title = this.getIntent().getStringExtra("title");
            author = this.getIntent().getStringExtra("author");
            text = this.getIntent().getStringExtra("text");

        }

         mTextView = (TextView) findViewById(R.id.titlePoem);
         mTextView.setMovementMethod(new ScrollingMovementMethod());
         mTextView.setText(title);

        mTextView = (TextView) findViewById(R.id.authorPoem);
        mTextView.setMovementMethod(new ScrollingMovementMethod());
        mTextView.setText(author);

         mTextView = (TextView) findViewById(R.id.textPoem);
         mTextView.setMovementMethod(new ScrollingMovementMethod());
         mTextView.setText(text);

        setToolbar();
    }


    public void setToolbar(){
        TextView toolbar_title;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.project_title));

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
  /*          case R.id.unfavourite:
                clicksFavorite++;
                if(clicksFavorite%2==1)
                    item.setIcon(R.drawable.icon_favorite);
                else
                    item.setIcon(R.drawable.icon_unfavorite);

                return true;*/
            case android.R.id.home:
                finish(); //closing the activity , the remaining one being the listActivity
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
