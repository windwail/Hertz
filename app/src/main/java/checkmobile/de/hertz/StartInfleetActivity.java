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
import checkmobile.de.hertz.gson.GsonInfleet;
import checkmobile.de.hertz.gson.GsonInfleetStart;
import checkmobile.de.hertz.helper.ProcessesHelper;

@EActivity(R.layout.activity_start_infleet)
public class StartInfleetActivity extends AppCompatActivity {

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
        pg.setFinished(false);
        pg.setCreateDate(new DateTime());

        GsonInfleetStart gis = new GsonInfleetStart();
        gis.setDriverName(driverName.getText().toString());
        gis.setTruckLicensePlate(truckLicensePlate.getText().toString());
        gis.setDriversEmail(driverEmail.getText().toString());
        gis.setNumberOfCars((int)numberOfCars.getValue());
        gis.setGeneralCondition(generalCondition.getSelectedItem().toString());
        gis.setDeliveryNoteNo(deliveryNote.getText().toString());

        pg.setVariablesGson(gsonBuilder.create().toJson(gis));

        if(processGroupDao.create(pg) != 1) {
            throw new RuntimeException("Cant create processGroup in StratrInfleet activity.");
        }

        // Create ProcessGroup for each car. It will contain all processes.
        for(int i=0; i < (int)numberOfCars.getValue(); i++ ) {
            ProcessGroup carProcessGroup = new ProcessGroup(ProcessGroup.Type.INFLEET);
            carProcessGroup.setFinished(false);
            carProcessGroup.setCreateDate(new DateTime());
            carProcessGroup.setParent(pg);

            GsonInfleet gson = new GsonInfleet();
            gson.setIndex(i);
            gson.setCarsCount((int)numberOfCars.getValue());

            carProcessGroup.setVariables(gson);
            processGroupDao.create(carProcessGroup);

            processDao.create(carProcessGroup.constructProcess(Process.Type.CAR_INFO));
            processDao.create(carProcessGroup.constructProcess(Process.Type.CAPTURE_MILEAGE));
            processDao.create(carProcessGroup.constructProcess(Process.Type.CAPTURE_FUEL));
            processDao.create(carProcessGroup.constructProcess(Process.Type.DAMAGE));
            processDao.create(carProcessGroup.constructProcess(Process.Type.OVERVIEW_PHOTOS));


            //processGroupDao.refresh(pg);
        }

        //Toast.makeText(getApplicationContext(), pg.getVariablesGson(), Toast.LENGTH_LONG).show();

        finish();
        Intent i = new Intent(getApplicationContext(), ProcessesActivity.class);
        i.putExtra(ProcessesHelper.PROCESS_GROUP_ID, pg.getId());
        i.putExtra(ProcessesHelper.CAR_INDEX, 0);
        i.putExtra(ProcessesHelper.CAR_COUNT, (int)numberOfCars.getValue());
        startActivity(i);

    }
}
