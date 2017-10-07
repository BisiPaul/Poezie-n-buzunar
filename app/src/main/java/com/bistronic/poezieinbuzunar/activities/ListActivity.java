package com.bistronic.poezieinbuzunar.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.bistronic.poezieinbuzunar.R;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView mListView;

    private String title;
    private String author;
    private String text;

    ArrayList<Poem> poemList = new ArrayList<>();

    protected PoemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setToolbar();

        if(!isNetworkAvailable()){
            setContentView(R.layout.no_internet);

        }
        else {
            final Context context = this;

            ProgressDialog progressDialog = new ProgressDialog(ListActivity.this);
            progressDialog.setMessage("We are writing the poems...");

            mListView = (ListView) findViewById(R.id.listPoemsView);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Poem selectedPoem = poemList.get(position);
                    Intent detailIntent = new Intent(context, ReadPoemActivity.class);
                    detailIntent.putExtra("title", selectedPoem.title);
                    detailIntent.putExtra("author", selectedPoem.author);
                    detailIntent.putExtra("text", selectedPoem.text);

                    detailIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                    Bundle extras = new Bundle();
                    extras.putString("title", selectedPoem.title);
                    extras.putString("author", selectedPoem.author);
                    extras.putString("text", selectedPoem.text);

                    detailIntent.putExtras(extras);
                    startActivity(detailIntent);
                }

            });

            new FetchPoems(progressDialog).execute();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    public void setToolbar(){
        TextView toolbar_title;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.project_title));

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

    @Override
    public void onBackPressed() {

    }

    class FetchPoems extends AsyncTask<Void, Void, ArrayList<Poem>> {
        ProgressDialog mProgressDialog;
        protected ArrayList<Poem> poemListAux = new ArrayList<>();

        public FetchPoems(ProgressDialog progressDialog){
            this.mProgressDialog = progressDialog;
        }

        public void onPreExecute() {
            mProgressDialog.show();
        }

        public ArrayList<Poem> doInBackground(Void... x) {
            ParseObject Poems = new ParseObject("Poems");
            ParseQuery<ParseObject> query = ParseQuery.getQuery("Poems");

            try{
                List<ParseObject> parseList = query.find();

                for (Integer i = 0; i < parseList.size(); i++) {
                    Poem tempPoem = new Poem(
                            parseList.get(i).getString("title"),
                            parseList.get(i).getString("author"),
                            parseList.get(i).getString("text"));
                    poemListAux.add(tempPoem);
                }
            } catch(ParseException e){
                e.printStackTrace();
            }

            return poemListAux;
        }

        @Override
        public void onPostExecute(ArrayList<Poem> poemListAux) {
            //super.onPostExecute(poemListAux);
            mProgressDialog.dismiss();
            poemList = poemListAux;
            adapter = new PoemAdapter(ListActivity.this, poemListAux);
            mListView.setAdapter(adapter);
        }

        public ArrayList<Poem> getPoemList(){
            return poemListAux;
        }
    }

    public  void refreshActivity(View v){
        if(isNetworkAvailable()){
            findViewById(R.id.no_internet).setVisibility(View.GONE);
            startActivity(getIntent());
            this.finish();

        }

    }

}

