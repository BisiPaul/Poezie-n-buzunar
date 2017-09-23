package com.bistronic.poezieinbuzunar.activities;

import android.content.Context;
import android.content.Intent;
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
import com.parse.ParseUser;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    ArrayList<Poem> poemList= new ArrayList<>(2);
    private Poem poem1 = new Poem("MĂ ASCUND","Robert Șerban","Robert Șerban - MĂ ASCUND\n\n "+ "mă ascund în clopotul cel mare al bisericii\n" +
            "şi mă rog să nu moară nimeni şi să nu fie sărbătoare\n" +
            "să nu crească dintr-odată rîul\n" +
            "şi focul să nu răbufnească\n" +
            "\n" +
            "mă ascund\n" +
            "lipit cu spatele de bronzul zgrunţuros\n" +
            "cu ochii ţintă la limba care atîrnă imobilă şi ucigătoare\n" +
            "\n" +
            "în clopotul cel mare e negură\n" +
            "dar simt cum palpită o singurătate mai groaznică decît\n" +
            "a mea\n" +
            "\n" +
            "aburi se ridică din mine şi se condensează pe limbă\n" +
            "de bronz\n" +
            "o umezesc şi o sărează\n" +
            "iar ea începe să se mişte încet\n" +
            "ca un mort înviat de ploaie.\n");
    private Poem poem2 = new Poem ("OMUL VIEŢII TALE","Magda Cârneci", "Magda Cârneci OMUL VIEŢII TALE\n" +
            "\n" +
            "Auzi o voce într-un magazin, întâlneşti fără să vrei o privire\n" +
            "te atinge până-n străfunduri, te uluieşte\n" +
            "şi brusc ştii că ai dat peste omul vieţii tale\n" +
            "omul demult aşteptat, mereu căutat\n" +
            "care ţi-a fost în fine trimis, când nu mai credeai că se poate\n" +
            "şi tocmai atunci eşti obosită peste măsură, nu te-ai machiat\n" +
            "părul nu ţi-e spălat, eşti numai în T-shirt şi blugi.\n" +
            "În zgomotul magazinului, rămâi nemişcată, încerci să gândeşti\n" +
            "o soluţie, priveşti pe furiş către omul acela\n" +
            "el caută un produs, e preocupat, nu te vede\n" +
            "dar tu ştii, e omul vieţii tale, simţi fără să înţelegi\n" +
            "intuieşti o transfigurare\n" +
            "ceva magnetic te leagă invizibil de el, te atrage\n" +
            "încerci să găseşti o apropiere, o cale, te prefaci că şi tu cauţi\n" +
            "produsul acela, dar deja omul vieţii tale a plecat mai departe,\n" +
            "tu îl urmezi\n" +
            "fără grai; nici nu i-ai văzut bine chipul\n" +
            "dar alura lui, chiar din spate, îţi indică că e omul vieţii tale\n" +
            "îl fixezi cu privirea, dar el nu simte nimic\n" +
            "şi oboseala ta e din ce în ce mai puternică\n" +
            "un fel de somn se lasă greu peste creier, caşti şi îţi vine\n" +
            "să te întinzi pe jos, un hău se deschide în tine,\n" +
            "el e omul vieţii tale şi nu ştie, nu simte\n" +
            "iar tu nu erai pregătită, erai adormită, nu mai sperai\n" +
            "privirea lui te-a atins până în străfunduri\n" +
            "ţi-a dat certitudinea aceea totală, absurdă\n" +
            "bucuria uriaşă este posibilă\n" +
            "perfecţiunea este posibilă\n" +
            "el e omul vieţii tale, omul îndelung aşteptat\n" +
            "inima strânsă te doare şi ai vrea să vorbeşti\n" +
            "nu găseşti cuvinte, totul pare ridicol\n" +
            "îl urmezi de la mică distanţă, îţi aminteşti brusc\n" +
            "de parabola celor cinci fecioare nebune\n" +
            "care au uitat să-l aştepte pe mire cu candela aprinsă, curată\n" +
            "când el a venit pe neaşteptate şi le-a frânt inima.\n" +
            "Acum ştii - şi cu pieptul în flăcări\n" +
            "dar mută ca o umbră cenuşie din lumea cealaltă\n" +
            "îl urmezi pe cel care se îndepărtează vertiginos\n" +
            "printre raioanele magazinului, fără să-i poţi striga, de departe,\n" +
            "eşti omul vieţii mele, eşti desăvârşirea mea\n" +
            "eşti dezmărginirea mea\n" +
            "te-am aşteptat cum am putut, m-am pregătit pe cât am putut\n" +
            "Am uitat. Mi-am reamintit. Am uitat.\n" +
            "Nu vreau să te mai ratez. Eşti gloria lumii.\n" +
            "Acum eşti aici. Vino!\n");

    private Poem poem3= new Poem("1","2","");
    private Poem poem4= new Poem("1","2","");
    private Poem poem5= new Poem("1","2","");
    private Poem poem6= new Poem("1","2","");
    private Poem poem7= new Poem("1","2","");
    private Poem poem8= new Poem("1","2","");
    private Poem poem9= new Poem("1","2","");
    private Poem poem10= new Poem("eu","2","");
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mListView = (ListView) findViewById(R.id.listPoemsView);

      // final ArrayList<Poem> poemList = Poem.getPoemsFromFile("poems.json", this);
        poemList.add(poem1);
        poemList.add(poem2);
        poemList.add(poem3);
        poemList.add(poem4);
        poemList.add(poem5);
        poemList.add(poem6);
        poemList.add(poem7);
        poemList.add(poem8);
        poemList.add(poem9);
        poemList.add(poem10);

        PoemAdapter adapter = new PoemAdapter(this, poemList);
        mListView.setAdapter(adapter);

        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Poem selectedPoem = poemList.get(position);

                Intent detailIntent = new Intent(context, ReadPoemActivity.class);

               // detailIntent.putExtra("title", selectedPoem.title);
                //detailIntent.putExtra("author", selectedPoem.author);
                detailIntent.putExtra("text", selectedPoem.text);
                startActivity(detailIntent);
            }

        });

        setToolbar();
    }

    public void setToolbar(){
        TextView toolbar_title;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.list_activity_title));
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
