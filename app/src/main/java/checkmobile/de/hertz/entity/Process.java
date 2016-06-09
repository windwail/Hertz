package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.Map;

import checkmobile.de.hertz.dao.ProcessDao;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "process", daoClass = ProcessDao.class)
public class Process {

    public Process(String processName) {
        name = processName;
    }

    @DatabaseField
    @Getter
    @Setter
    private String name;

    @DatabaseField
    @Getter
    @Setter
    private Date date;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    @Getter
    @Setter
    Map<String, Object> variables;

    @DatabaseField
    @Getter
    @Setter
    private Boolean finished;


}
