package checkmobile.de.hertz;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import checkmobile.de.hertz.dao.InfleetStartDao;
import checkmobile.de.hertz.db.DatabaseHelper;
import lombok.Getter;

@EActivity(R.layout.activity_start_infleet)
public class StartInfleetActivity extends AppCompatActivity {

    @OrmLiteDao(helper = DatabaseHelper.class)
    @Getter
    protected InfleetStartDao infleetStartDao;

    /*
    @Click
    public void startButton() {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(getApplicationContext(), "HIHIHI!", duration);
        toast.show();
    }
    */

}
