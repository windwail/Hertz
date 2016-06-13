package checkmobile.de.hertz.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import checkmobile.de.hertz.helper.ProcessesHelper;

/**
 * Created by icetusk on 13.06.16.
 */
public class CMActivity extends AppCompatActivity {

    protected RuntimeExceptionDao processGroupDao;

    protected RuntimeExceptionDao processDao;

    protected DatabaseHelper dbHelper;

    protected ProcessGroup processGroup;

    protected Process process;

    protected int process_id;

    protected int processGroup_id;

    protected void initData() {
        dbHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        processDao = dbHelper.getProcessDAO();
        processGroupDao = dbHelper.getProcessGroupDAO();

        Intent i = getIntent();

        if(i != null) {

            process_id = i.getIntExtra(ProcessesHelper.PROCESS_ID, -1);

            if(process_id > 0) {
                process = (Process) processDao.queryForId(process_id);
            }

            processGroup_id = i.getIntExtra(ProcessesHelper.PROCESS_GROUP_ID, -1);

            if(processGroup_id > 0) {
                processGroup = (ProcessGroup) processGroupDao.queryForId(processGroup_id);
            }
        }
    }
}
