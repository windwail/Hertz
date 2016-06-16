package checkmobile.de.hertz.applicaton;

import android.app.Application;

import com.facebook.stetho.Stetho;

import org.androidannotations.annotations.EApplication;

import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import lombok.Getter;
import lombok.Setter;


public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}
