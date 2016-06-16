package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

import checkmobile.de.hertz.dao.ImageDao;
import checkmobile.de.hertz.dao.ProcessDao;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by icetusk on 15.06.16.
 */

@DatabaseTable(tableName = "image", daoClass = ImageDao.class)
public class Image {

    //Database Field for Table Student
    @DatabaseField(generatedId = true)
    @Getter
    @Setter
    private int id;

    @DatabaseField(dataType = DataType.DATE_TIME)
    @Getter
    @Setter
    private DateTime createDate;

    @DatabaseField
    @Getter
    @Setter
    String variablesGson;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "process")
    @Getter
    @Setter
    private Process process;
}
