package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import checkmobile.de.hertz.adapter.MenuAdapter;
import checkmobile.de.hertz.adapter.ProcessesAdapter;
import checkmobile.de.hertz.adapter.StationAdapter;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class StationActivity  extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView=(ListView)findViewById(R.id.list_view);

        StationAdapter adapter = new StationAdapter(getApplicationContext(), R.layout.list_element);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent myIntent = new Intent(view.getContext(), MenuActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
