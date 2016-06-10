package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.Date;

import checkmobile.de.hertz.dao.ProcessDao;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "process", daoClass = ProcessDao.class)
public class Process implements Serializable{

    //Database Field for Table Student
    @DatabaseField (generatedId = true)
    @Getter
    @Setter
    private int id;


    public Process(String carInfo) {
    }

    public enum Type { INFLEET };

    public Process() {
    }

    @DatabaseField
    @Getter
    @Setter
    private String name;

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

    @DatabaseField
    @Getter
    @Setter
    private Boolean mandatory;

    @DatabaseField (foreign = true, foreignAutoRefresh = true, columnName = "parentGroup")
    @Getter
    @Setter
    private ProcessGroup parent;

}
