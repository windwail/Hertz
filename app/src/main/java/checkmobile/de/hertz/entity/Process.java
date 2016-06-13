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
public class Process implements Serializable {

    public enum Type {
        VEHICLE_ACCESSORIES,
        REMOVED_ACCESSORIES,
        MECHANICAL_FAULTS,
        TYRE_PRESSURE,
        CAPTURE_MILEAGE,
        CAPTURE_FUEL,
        OVERVIEW_PHOTOS,
        PARKING,
        DAMAGE,
        CAR_INFO
    }

    public Process() {
    }


    //Database Field for Table Student
    @DatabaseField(generatedId = true)
    @Getter
    @Setter
    private int id;

    @DatabaseField(dataType = DataType.ENUM_STRING, canBeNull = false)
    @Getter
    @Setter
    private Type type;

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
    private boolean finished;

    @DatabaseField
    @Getter
    @Setter
    private boolean mandatory;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "parentGroup")
    @Getter
    @Setter
    private ProcessGroup parent;

}
