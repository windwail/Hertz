package checkmobile.de.hertz.applicaton;

import android.app.Application;

import checkmobile.de.hertz.db.DatabaseHelper;

/**
 * Created by icetusk on 07.06.16.
 */
public class MyApplication extends Application{

    private DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        databaseHelper = new DatabaseHelper(getApplicationContext());
    }

}
