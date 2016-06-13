package checkmobile.de.hertz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import checkmobile.de.hertz.adapter.MenuAdapter;

public class MenuActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listView=(ListView)findViewById(R.id.list_view);

        MenuAdapter adapter = new MenuAdapter(getApplicationContext(), R.layout.list_element);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent myIntent = new Intent(view.getContext(), StartInfleetActivity_.class);
                startActivity(myIntent);
            }
        });
    }
}
