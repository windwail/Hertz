package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import checkmobile.de.hertz.dao.ProcessGroupDao;
import checkmobile.de.hertz.gson.CMGson;
import checkmobile.de.hertz.gson.GsonHelper;
import checkmobile.de.hertz.gson.GsonInfleet;
import checkmobile.de.hertz.gson.GsonInfleetStart;
import lombok.Getter;
import lombok.Setter;


@DatabaseTable(tableName = "processGroup", daoClass = ProcessGroupDao.class)
public class ProcessGroup implements Serializable {


    public ProcessGroup(Type type) {
        this.type = type;
    }

    public ProcessGroup() {
    }

    public enum Type { INFLEET, INFLEET_CHILD };

    //Database Field for Table Student
    @DatabaseField (generatedId = true)
    @Getter
    @Setter
    private int id;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "parentGroup")
    @Getter
    @Setter
    private ProcessGroup parent;

    @DatabaseField(dataType = DataType.DATE_TIME)
    @Getter
    @Setter
    private DateTime createDate;

    @DatabaseField
    @Getter
    @Setter
    String variablesGson;

    @DatabaseField
    @Getter
    @Setter
    private Boolean finished;

    @DatabaseField(dataType=DataType.ENUM_STRING, canBeNull = false)
    @Getter
    @Setter
    private Type type;


    @ForeignCollectionField
    @Getter
    @Setter
    Collection<Process> processes = new ArrayList<>();

    @ForeignCollectionField
    @Getter
    @Setter
    Collection<ProcessGroup> groups = new ArrayList<>();


    public void setVariables(CMGson gson) {
        variablesGson = gson.toString();
    }

    public Object getVariables() {
        Class varsClazz = null;
        switch (type) {
            case INFLEET:
                varsClazz = GsonInfleetStart.class;
                break;
            case INFLEET_CHILD:
                varsClazz = GsonInfleet.class;
                break;
        }
        return GsonHelper.getBuilder().fromJson(variablesGson, varsClazz);
    }

    public Process constructProcess(Process.Type type) {
        Process p = new Process();
        p.setCreateDate(new DateTime());
        p.setMandatory(true);
        p.setParent(this);
        p.setFinished(false);
        p.setType(type);
        return p;
    }
}
