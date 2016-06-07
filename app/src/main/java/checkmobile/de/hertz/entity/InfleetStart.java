package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by icetusk on 07.06.16.
 */

@DatabaseTable(tableName = "infleetStart")
public class InfleetStart {

    @DatabaseField(generatedId = true)
    private Integer Id;

    @DatabaseField
    private Date date;

    @DatabaseField
    private Boolean finished;

    @DatabaseField
    private Integer carCount;

    @DatabaseField
    private String driverName;

}
