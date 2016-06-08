package checkmobile.de.hertz;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import checkmobile.de.hertz.dao.InfleetStartDao;
import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.InfleetStart;
import lombok.Getter;
import lombok.Setter;

@EActivity(R.layout.activity_start_infleet)
public class StartInfleetActivity extends AppCompatActivity {

    @OrmLiteDao(helper = DatabaseHelper.class)
    @Getter
    @Setter
    protected InfleetStartDao infleetStartDao;

    @Click
    public void startButton() {
        int duration = Toast.LENGTH_SHORT;

        InfleetStart start = new InfleetStart();
        start.setCarCount((int)Math.round(Math.random()*10));
        start.setDate(new Date());
        start.setDriverName("Vasya "+(int)Math.round(Math.random()*1000));
        start.setFinished(false);

        try {
            infleetStartDao.create(start);

            Toast toast = Toast.makeText(getApplicationContext(), "HIHIHI!"+infleetStartDao.countOf(), duration);
            toast.show();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
