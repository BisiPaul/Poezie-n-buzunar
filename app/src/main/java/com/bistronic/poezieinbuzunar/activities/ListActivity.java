package com.bistronic.poezieinbuzunar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bistronic.poezieinbuzunar.R;
import com.bistronic.poezieinbuzunar.activities.ReadPoemActivity;
import com.parse.ParseUser;

public class ListActivity extends AppCompatActivity {
    private ListView list;
    private  String [] items = {"Poezie1","Poezie2","Poezie3"}; //test items
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (ListView)findViewById(R.id.listPoemsView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_view_single_row,R.id.textViewItemList,items);
        list.setAdapter(adapter);
    }

    public void displayPoemOnScreen(View v) {
        Intent intent = new Intent(this, ReadPoemActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_base_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    protected void logout() {
        ParseUser.getCurrentUser().logOut();
        Intent mainIntent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

}
