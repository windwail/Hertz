package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import checkmobile.de.hertz.adapter.ProcessesAdapter;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class ProcessesActivity extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processes);

        listView=(ListView)findViewById(R.id.list_view);

        ProcessesAdapter adapter = new ProcessesAdapter(getApplicationContext(), R.layout.process_element);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent myIntent = new Intent(view.getContext(), ProcessesHelper.getList().get(position).getActivityClass());

                if(ProcessesHelper.getList().get(position).getHelper() != null) {
                    myIntent.putExtra(SelectableListActivity.HELPER_EXTRA, ProcessesHelper.getList().get(position).getHelper());
                }

                startActivity(myIntent);
            }
        });

        Button done = (Button) findViewById(R.id.doneButton);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getApplicationContext(), DamageActivity.class);
                startActivity(menuIntent);
            }
        });
    }
}
