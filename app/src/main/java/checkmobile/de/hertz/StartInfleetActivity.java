package checkmobile.de.hertz;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.HashMap;

import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.InfleetStart;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;

@EActivity(R.layout.activity_start_infleet)
public class StartInfleetActivity extends AppCompatActivity {

    protected RuntimeExceptionDao processGroupDao;

    protected RuntimeExceptionDao processDao;

    protected DatabaseHelper dbHelper;

    protected GsonBuilder gsonBuilder;

    @AfterViews
    public void afterViews() {
        dbHelper = OpenHelperManager.getHelper(this,DatabaseHelper.class);
        processDao = dbHelper.getProcessDAO();
        processGroupDao = dbHelper.getProcessGroupDAO();
        gsonBuilder = new GsonBuilder();
    }

    @Click
    public void startButton() {

        ProcessGroup pg = new ProcessGroup(ProcessGroup.Type.INFLEET);
        pg.setFinished(true);
        pg.setCreateDate(new DateTime());
        pg.setName("Infleeting start");




        processGroupDao.create(pg);

        for(int i=0; i<3; i++) {
            Process p = new Process();
            p.setName("Process "+i);
            p.setFinished(true);
            p.setCreateDate(new DateTime());
            p.setMandatory(true);
            p.setParent(pg);
            processDao.create(p);
        }

        //processGroupDao.refresh(pg);

        Toast.makeText(getApplicationContext(), "size" + pg.getProcesses().size(), Toast.LENGTH_LONG).show();

    }
}
