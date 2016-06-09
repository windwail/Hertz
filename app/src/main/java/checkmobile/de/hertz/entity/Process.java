package checkmobile.de.hertz.entity;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import checkmobile.de.hertz.dao.ProcessDao;
import lombok.Getter;
import lombok.Setter;

@DatabaseTable(tableName = "process", daoClass = ProcessDao.class)
public class Process {

    public enum Type { INFLEET };

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

    @DatabaseField
    @Getter
    @Setter
    private Boolean mandatory;

    @DatabaseField (foreign = true, foreignAutoRefresh = true, columnName = "parentGroup")
    @Getter
    @Setter
    private ProcessGroup parent;

    @ForeignCollectionField
    @Getter
    @Setter
    Collection<Process> processes;

}
