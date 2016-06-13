package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.GsonBuilder;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.bundle.BundleHelper;
import org.joda.time.DateTime;

import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import checkmobile.de.hertz.fragment.CMNumberPicker;
import checkmobile.de.hertz.gson.GsonInfleetStart;

@EActivity(R.layout.activity_start_infleet)
public class StartInfleetActivity extends AppCompatActivity {

    public static final String PROCESS_GROUP_ID = "process_group_id";

    protected RuntimeExceptionDao processGroupDao;

    protected RuntimeExceptionDao processDao;

    protected DatabaseHelper dbHelper;

    protected GsonBuilder gsonBuilder;

    @ViewById
    EditText deliveryNote;

    @ViewById
    EditText truckLicensePlate;

    @ViewById
    EditText driverName;

    @ViewById
    EditText driverEmail;

    @FragmentById
    CMNumberPicker numberOfCars;

    @ViewById
    Spinner generalCondition;

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

        GsonInfleetStart gis = new GsonInfleetStart();
        gis.setDriverName(driverName.getText().toString());
        gis.setTruckLicensePlate(truckLicensePlate.getText().toString());
        gis.setDriversEmail(driverEmail.getText().toString());
        gis.setNumberOfCars((int)numberOfCars.getValue());
        gis.setGeneralCondition(generalCondition.getSelectedItem().toString());
        gis.setDeliveryNoteNo(deliveryNote.getText().toString());

        pg.setVariablesGson(gsonBuilder.create().toJson(gis));

        int group_id = processGroupDao.create(pg);

        for(int i=0; i<3; i++) {
            Process p = new Process();
            p.setName("Process "+i);
            p.setFinished(true);
            p.setCreateDate(new DateTime());
            p.setMandatory(true);
            p.setParent(pg);

            processDao.create(p);
        }

        processGroupDao.refresh(pg);

        Toast.makeText(getApplicationContext(), pg.getVariablesGson(), Toast.LENGTH_LONG).show();

        finish();
        Intent i = new Intent(getApplicationContext(), ProcessesActivity.class);
        i.putExtra(PROCESS_GROUP_ID, group_id);
        startActivity(i);

    }
}
