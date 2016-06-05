package austin.jgram;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;



//import austin.jgram.Settings.Preferences;

public class MainActivity extends AppCompatActivity {

    ListView sentences;
    StringDatabase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Logout Option", Snackbar.LENGTH_LONG)
                  //      .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sentences = (ListView) findViewById(R.id.sentences);
        //NOT WORKING YET
        //String url = "https://www.wanikani.com/api/user/7da5e5dd918c6aeec6f7e2a8063853da/kanji/1";

        String[] values = new String[] {
                "３年というは長い時間だと私は思う",
                "あなたが料理するのを見た",
                "あの日は強い風が吹いていました"
        };

        //UNCOMMENT FOR TESTING
        data = new StringDatabase(this);
        data.createData(values, 3);
        data.getAllWords();
        values = data.data;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        // Assign adapter to ListView
        sentences.setAdapter(adapter);
        // ListView Item Click Listener
        sentences.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) sentences.getItemAtPosition(position);
                //translationFragment trans = new translationFragment();
                //trans.isVisible();
                // Show Alert

                if (itemPosition == 2) {
                    //LoadingScreen load = new LoadingScreen(getApplicationContext());
                    Intent settings_intent = new Intent(getApplicationContext(), austin.jgram.LoadingScreen.class);
                    startActivity(settings_intent);

                } else {
                    Toast.makeText(getApplicationContext(),
                            "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                            .show();
                }
            }

        });
        //LoadingScreen load = new LoadingScreen(this);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settings_intent = new Intent(this, austin.jgram.Settings.Preferences.class);
            startActivity(settings_intent);
            return true;
        }
        if (id == R.id.grammar_test) {
            Intent intent = new Intent(getApplicationContext(), grammarTest.class);
            startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}
