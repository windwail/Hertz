package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.adapter.ProcessesAdapter;
import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import checkmobile.de.hertz.gson.Damage;
import checkmobile.de.hertz.helper.ProcessesHelper;

public class ProcessesActivity extends CMActivity {

    public static final int PROCESS_TYPE_SHIFT = 1000;

    public static final int FINISH_INFLEET = 0;

    ListView listView;

    List<Process> processes;

    protected ProcessGroup currentCarProcesssesGroup;

    protected ArrayList<ProcessGroup> neighborGroups;

    protected int carIndex;

    protected int carCount;

    protected Button prev;

    protected Button next;


    protected TextView label;


    protected void initAdapter() {

        processGroupDao.update(currentCarProcesssesGroup);

        processes =  new ArrayList<>(currentCarProcesssesGroup.getProcesses());

        ProcessesAdapter adapter = new ProcessesAdapter(getApplicationContext(), R.layout.process_element, processes);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent myIntent = new Intent(view.getContext(), ProcessesHelper.getActivityClass(processes.get(position)));
                myIntent.putExtra(ProcessesHelper.PROCESS_ID, processes.get(position).getId());
                ProcessesActivity.this.startActivityForResult(myIntent,processes.get(position).getType().ordinal()+PROCESS_TYPE_SHIFT);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processes);

        Intent i = getIntent();

        carIndex = i.getIntExtra(ProcessesHelper.CAR_INDEX, -1);

        carCount =  i.getIntExtra(ProcessesHelper.CAR_COUNT, -1);

        neighborGroups = new ArrayList<>(processGroup.getGroups());

        currentCarProcesssesGroup = neighborGroups.get(carIndex);

        listView=(ListView)findViewById(R.id.list_view);

        prev = (Button)findViewById(R.id.prev);

        next = (Button)findViewById(R.id.next);

        label = (TextView)findViewById(R.id.title);


        if(carIndex+1 >= carCount) {
            next.setText("FINISH");
        }

        if(carIndex == 0) {
            prev.setText("CANCEL");
        }

        label.setText("Car "+(carIndex+1)+ " of "+carCount);

        initAdapter();


    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        initAdapter();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FINISH_INFLEET) {
            if( resultCode == RESULT_OK) {
                Intent intent = this.getIntent();
                this.setResult(RESULT_OK, intent);
                finish();
            }
        }

        Process.Type type = null;

        if(requestCode >= PROCESS_TYPE_SHIFT && requestCode <= (Process.Type.values().length + PROCESS_TYPE_SHIFT))  {
            type = Process.Type.values()[requestCode-PROCESS_TYPE_SHIFT];
        }

        switch(type) {
            case OVERVIEW_PHOTOS:

                String[] images = data.getStringArrayExtra("images");

                process = (Process) processDao.queryForId(data.getIntExtra(ProcessesHelper.PROCESS_ID, -1));

                for()


                String[] images = data.getStringArrayExtra("images");

                for(Damage d: damages) {
                    if(d.getUid().equalsIgnoreCase(damage.getUid())) {
                        damage = d;
                        break;
                    }
                }

                damage.setImages(images);

                process.setDamages(damages);
                processDao.update(process);


                break;
        }
    }

    public void onPrev(View button) {
        finish();

        if(carIndex+1 == 0) {
            // do nothing;
        } else {
            Intent i = new Intent(getApplicationContext(), ProcessesActivity.class);
            i.putExtra(ProcessesHelper.PROCESS_GROUP_ID, processGroup.getId());
            i.putExtra(ProcessesHelper.CAR_INDEX, carIndex - 1);
            i.putExtra(ProcessesHelper.CAR_COUNT, carCount);
            startActivity(i);
        }
    }

    public void onNext(View button) {
        finish();

        if(carIndex+1 >= carCount) {
            Intent menuIntent = new Intent(getApplicationContext(), CarInfleetOverview.class);
            startActivityForResult(menuIntent, FINISH_INFLEET);
        } else {
            Intent i = new Intent(getApplicationContext(), ProcessesActivity.class);
            i.putExtra(ProcessesHelper.PROCESS_GROUP_ID, processGroup.getId());
            i.putExtra(ProcessesHelper.CAR_INDEX, carIndex + 1);
            i.putExtra(ProcessesHelper.CAR_COUNT, carCount);
            startActivity(i);
        }
    }
}
