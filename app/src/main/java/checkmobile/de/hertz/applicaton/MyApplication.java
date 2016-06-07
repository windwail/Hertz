package checkmobile.de.hertz.applicaton;

import android.app.Application;

import org.androidannotations.annotations.EApplication;
import org.androidannotations.ormlite.annotations.OrmLiteDao;

import checkmobile.de.hertz.dao.InfleetStartDao;
import checkmobile.de.hertz.db.DatabaseHelper;
import checkmobile.de.hertz.entity.InfleetStart;
import lombok.Getter;

@EApplication
public class MyApplication extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
    }



}
