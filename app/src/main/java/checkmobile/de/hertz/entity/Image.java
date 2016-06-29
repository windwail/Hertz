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
@Getter
@Setter
public class Image {

    public Image() {
    }

    public Image(String path) {
        createDate = new DateTime();
        this.path = path;
    }

    //Database Field for Table Student
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(dataType = DataType.DATE_TIME)
    private DateTime createDate;

    @DatabaseField
    private String variablesGson;

    @DatabaseField
    private String path;

    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "process")
    private Process process;


}
