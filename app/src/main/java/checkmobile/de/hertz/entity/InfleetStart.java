package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import checkmobile.de.hertz.dao.InfleetStartDao;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by icetusk on 07.06.16.
 */

@DatabaseTable(tableName = "infleetStart", daoClass = InfleetStartDao.class)
public class InfleetStart {

    @DatabaseField(generatedId = true)
    @Getter
    @Setter
    private Integer Id;

    @DatabaseField
    @Getter
    @Setter
    private Date date;

    @DatabaseField
    @Getter
    @Setter
    private Boolean finished;

    @DatabaseField
    @Getter
    @Setter
    private Integer carCount;

    @DatabaseField
    @Getter
    @Setter
    private String driverName;

}
