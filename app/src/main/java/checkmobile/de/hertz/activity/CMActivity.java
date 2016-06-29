package checkmobile.de.hertz.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import checkmobile.de.hertz.DamageDetailsActivity;
import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import checkmobile.de.hertz.gson.Damage;
import checkmobile.de.hertz.helper.ProcessesHelper;

/**
 * Created by icetusk on 13.06.16.
 */
public class CMActivity extends AppCompatActivity {

    protected RuntimeExceptionDao processGroupDao;

    protected RuntimeExceptionDao processDao;

    protected RuntimeExceptionDao imageDao;

    protected DatabaseHelper dbHelper;

    protected ProcessGroup processGroup;

    protected Process process;

    protected int process_id;

    protected int processGroup_id;


    protected void initData() {
        dbHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        processDao = dbHelper.getProcessDAO();
        processGroupDao = dbHelper.getProcessGroupDAO();
        imageDao = dbHelper.getImageDAO();

        Intent i = getIntent();

        if(i != null) {

            process_id = i.getIntExtra(ProcessesHelper.PROCESS_ID, -1);

            if(process_id > 0) {
                process = (Process) processDao.queryForId(process_id);
                Log.e("DEBUG:","Loaded process "+process.getId());
            }

            processGroup_id = i.getIntExtra(ProcessesHelper.PROCESS_GROUP_ID, -1);

            if(processGroup_id > 0) {
                processGroup = (ProcessGroup) processGroupDao.queryForId(processGroup_id);
                Log.e("DEBUG:","Loaded group "+processGroup.getId());
            }
        }
    }

    protected Intent getIntent(Class<? extends Activity> a, Context c) {

        Intent myIntent = new Intent(c, a);
        if(process != null) {
            myIntent.putExtra(ProcessesHelper.PROCESS_ID, process.getId());
        }
        if(processGroup != null) {
            myIntent.putExtra(ProcessesHelper.PROCESS_GROUP_ID, processGroup.getId());
        }

        return  myIntent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


}
