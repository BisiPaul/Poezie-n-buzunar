package com.bistronic.poezieinbuzunar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by Larisa on 05.08.2017.
 */
public class ReadPoemActivity extends AppCompatActivity {
    private  int clicksFavorite=0;
    private String testPoem = "\t\t\n" +
            "\t\tMihai Eminescu\n" +
            "Luceafărul \n" +
            "\n" +
            "A fost odată ca-n poveşti,\n" +
            "A fost ca niciodată.\n" +
            "Din rude mari împărăteşti,\n" +
            "O prea frumoasă fată.\n" +
            "\n" +
            "Şi era una la părinţi\n" +
            "Şi mândră-n toate cele,\n" +
            "Cum e Fecioara între sfinţi\n" +
            "Şi luna între stele.\n" +
            "\n" +
            "Din umbra falnicelor bolţi\n" +
            "Ea pasul şi-l îndreaptă\n" +
            "Lângă fereastră, unde-n colţ\n" +
            "Luceafărul aşteaptă.\n" +
            "\n" +
            "Privea în zare cum pe mări\n" +
            "Răsare şi străluce,\n" +
            "Pe mişcătoarele cărări\n" +
            "Corăbii negre duce.\n" +
            "\n" +
            "Îl vede azi, îl vede mâini,\n" +
            "Astfel dorinţa-i gata;\n" +
            "El iar, privind de săptămâni,\n" +
            "Îi cade draga fată.\n" +
            "\n" +
            "Cum ea pe coate-şi răzima\n" +
            "Visând ale ei tâmple,\n" +
            "De dorul lui şi inima\n" +
            "Şi sufletu-i se împle.\n" +
            "\n" +
            "Şi cât de viu s-aprinde el\n" +
            "În orişicare sară,\n" +
            "Spre umbra negrului castel\n" +
            "Când ea o să-i apară.\n" +
            "\n" +
            "...\n" +
            "\n" +
            "Şi pas cu pas pe urma ei\n" +
            "Alunecă-n odaie,\n" +
            "Ţesând cu recile-i scântei\n" +
            "O mreajă de văpaie.\n" +
            "\n" +
            "Şi când în pat se-ntinde drept\n" +
            "Copila să se culce,\n" +
            "I-atinge mâinile pe piept,\n" +
            "I-nchide geana dulce;\n" +
            "\n" +
            "Şi din oglindă luminiş\n" +
            "Pe trupu-i se revarsă,\n" +
            "Pe ochii mari, bătând închişi\n" +
            "Pe faţa ei întoarsă.\n" +
            "\n" +
            "Ea îl privea cu un surâs,\n" +
            "El tremura-n oglindă,\n" +
            "Căci o urma adânc în vis\n" +
            "De suflet să se prindă.\n" +
            "\n" +
            "Iar ea vorbind cu el în somn,\n" +
            "Oftând din greu suspină:\n" +
            "- O, dulce-al nopţii mele domn,\n" +
            "De ce nu vii tu? Vină!\n" +
            "\n" +
            "Cobori în jos, luceafăr blând,\n" +
            "Alunecând pe-o rază,\n" +
            "Pătrunde-n casă şi în gând\n" +
            "Şi viaţa-mi luminează!\n" +
            "\n" +
            "El asculta tremurător,\n" +
            "Se aprindea mai tare\n" +
            "Şi s-arunca fulgerător,\n" +
            "Se cufunda în mare;\n" +
            "\n" +
            "Şi apa unde-au fost căzut\n" +
            "În cercuri se roteşte,\n" +
            "Şi din adânc necunoscut\n" +
            "Un mândru tânăr creşte.\n" +
            "\n" +
            "Uşor el trece ca pe prag\n" +
            "Pe marginea ferestei\n" +
            "Şi ţine-n mână un toiag\n" +
            "Încununat cu trestii.\n" +
            "\n" +
            "Părea un tânăr voievod\n" +
            "Cu păr de aur moale,\n" +
            "Un vânăt giulgi se-ncheie nod\n" +
            "Pe umerele goale.\n" +
            "\n" +
            "Iar umbra feţei străvezii\n" +
            "E albă ca de ceară -\n" +
            "Un mort frumos cu ochii vii\n" +
            "Ce scânteie-n afară.\n" +
            "\n" +
            "- Din sfera mea venii cu greu\n" +
            "Ca să-ţi urmez chemarea,\n" +
            "Iar cerul este tatăl meu\n" +
            "Şi mumă-mea e marea.\n" +
            "\n" +
            "Ca în cămara ta să vin,\n" +
            "Să te privesc de-aproape,\n" +
            "Am coborât cu-al meu senin\n" +
            "Şi m-am născut din ape.\n" +
            "\n" +
            "O, vin'! odorul meu nespus,\n" +
            "Şi lumea ta o lasă;\n" +
            "Eu sunt luceafărul de sus,\n" +
            "Iar tu să-mi fii mireasă.\n" +
            "\n" +
            "Colo-n palate de mărgean\n" +
            "Te-oi duce veacuri multe,\n" +
            "Şi toată lumea-n ocean\n" +
            "De tine o s-asculte.\n" +
            "\n" +
            "- O, eşti frumos, cum numa-n vis\n" +
            "Un înger se arată,\n" +
            "Dară pe calea ce-ai deschis\n" +
            "N-oi merge niciodată;\n" +
            "\n" +
            "Străin la vorbă şi la port,\n" +
            "Luceşti fără de viaţă,\n" +
            "Căci eu sunt vie, tu eşti mort,\n" +
            "Şi ochiul tău mă-ngheaţă.";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_poem);


        //for the back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //display the poem
        TextView textElement = (TextView)findViewById(R.id.textViewPoem);
        textElement.setMovementMethod(new ScrollingMovementMethod());

        textElement.setText(testPoem);
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
