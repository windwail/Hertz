package checkmobile.de.hertz.domain;

import checkmobile.de.hertz.helper.CMListHelper;

/**
 * Created by icetusk on 23.05.16.
 */
public class CMProcess {

    private String name;

    private Class activityClass;

    private CMListHelper helper;

    private boolean finished;

    private boolean required;


    public CMProcess(String name, Class activityClass) {
        this.activityClass = activityClass;
        this.name = name;
    }

    public CMProcess(String name, Class activityClass, CMListHelper helper) {
        this.activityClass = activityClass;
        this.helper = helper;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getActivityClass() {
        return activityClass;
    }

    public void setActivityClass(Class activityClass) {
        this.activityClass = activityClass;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public CMListHelper getHelper() {
        return helper;
    }

    public void setHelper(CMListHelper helper) {
        this.helper = helper;
    }
}
