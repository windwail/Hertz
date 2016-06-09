package checkmobile.de.hertz.applicaton;

import android.app.Application;

import com.facebook.stetho.Stetho;

import org.androidannotations.annotations.EApplication;

import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;
import lombok.Getter;
import lombok.Setter;

@EApplication
public class MyApplication extends Application{

    @Getter
    @Setter
    ProcessGroup currentGroup;

    @Getter
    @Setter
    Process currentProcess;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}
