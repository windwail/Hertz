package checkmobile.de.hertz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import checkmobile.de.hertz.R;
import checkmobile.de.hertz.activity.CMActivity;
import checkmobile.de.hertz.helper.ProcessesHelper;

/**
 * Created by icetusk on 13.06.16.
 */
@EActivity(R.layout.fragment_car_info_infleet)
public class CarInfoInfleetActivity extends CMActivity {

    @AfterViews
    public void init() {
        super.initData();
        Intent i = getIntent();
        i.getIntExtra(ProcessesHelper.PROCESS_ID, -1);
    }

    @ViewById
    Button saveButton;

    @Click
    public void saveButton() {

        process.setFinished(true);

        processDao.update(process);

        finish();
    }
}
