package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import checkmobile.de.hertz.adapter.ProcessesAdapter;
import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class ProcessesActivity extends AppCompatActivity {

    ListView listView;

    Collection<Process> processes;

    protected RuntimeExceptionDao processGroupDao;

    protected RuntimeExceptionDao processDao;

    protected DatabaseHelper dbHelper;

    protected ProcessGroup pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processes);

        dbHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        processDao = dbHelper.getProcessDAO();
        processGroupDao = dbHelper.getProcessGroupDAO();

        listView=(ListView)findViewById(R.id.list_view);

        int group_id = getIntent().getIntExtra(StartInfleetActivity.PROCESS_GROUP_ID, -1);

        pg = (ProcessGroup) processGroupDao.queryForId(group_id);

        processes = pg.getProcesses();

        ProcessesAdapter adapter = new ProcessesAdapter(getApplicationContext(), R.layout.process_element, new ArrayList<Process>(processes));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Intent myIntent = new Intent(view.getContext(), ProcessesHelper.getList().get(position).getActivityClass());

              // processes.

               // ProcessesActivity.this.startActivity(myIntent);
            }
        });

        Button done = (Button) findViewById(R.id.doneButton);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent menuIntent = new Intent(getApplicationContext(), DamageActivity.class);
                //startActivity(menuIntent);
            }
        });
    }
}
