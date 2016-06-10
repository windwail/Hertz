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
import lombok.Getter;
import lombok.Setter;


@DatabaseTable(tableName = "processGroup", daoClass = ProcessGroupDao.class)
public class ProcessGroup implements Serializable {

    public ProcessGroup(Type type) {
        this.type = type;
    }

    public ProcessGroup() {
    }

    public enum Type { INFLEET };

    //Database Field for Table Student
    @DatabaseField (generatedId = true)
    @Getter
    @Setter
    private int id;

    @DatabaseField
    @Getter
    @Setter
    private String name;

    @DatabaseField(dataType = DataType.DATE_TIME)
    @Getter
    @Setter
    private DateTime createDate;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    @Getter
    @Setter
    String variablesGson;

    @DatabaseField
    @Getter
    @Setter
    private Boolean finished;

    @DatabaseField(dataType=DataType.ENUM_INTEGER)
    @Getter
    @Setter
    private Type type;


    @ForeignCollectionField
    @Getter
    @Setter
    Collection<Process> processes = new ArrayList<>();


}
