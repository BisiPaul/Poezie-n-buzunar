package com.bistronic.poezieinbuzunar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

}
