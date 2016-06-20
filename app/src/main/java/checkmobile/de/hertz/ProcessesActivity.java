package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.adapter.ProcessesAdapter;
import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class ProcessesActivity extends CMActivity {



    ListView listView;

    List<Process> processes;

    protected void initAdapter() {
        super.initData();

        Log.e("LAST","id:"+dbHelper.queryLast());

        processes = Arrays.asList(processGroup.getProcesses().toArray(new Process[]{}));

        ProcessesAdapter adapter = new ProcessesAdapter(getApplicationContext(), R.layout.process_element, processes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent myIntent = new Intent(view.getContext(), ProcessesHelper.getActivityClass(processes.get(position)));
                myIntent.putExtra(ProcessesHelper.PROCESS_ID, processes.get(position).getId());
                ProcessesActivity.this.startActivity(myIntent);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processes);

        listView=(ListView)findViewById(R.id.list_view);

        initAdapter();

        Button done = (Button) findViewById(R.id.doneButton);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(getApplicationContext(), CarInfleetOverview.class);
                startActivityForResult(menuIntent, FINISH_INFLEET);
            }
        });
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initAdapter();
    }

    public static final int FINISH_INFLEET = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == FINISH_INFLEET) {

            if( resultCode == RESULT_OK) {
                Intent intent = this.getIntent();
                this.setResult(RESULT_OK, intent);
            }
            finish();

        }
    }
}
