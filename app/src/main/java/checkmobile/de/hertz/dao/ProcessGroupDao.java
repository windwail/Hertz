package checkmobile.de.hertz.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import checkmobile.de.hertz.entity.Process;
import checkmobile.de.hertz.entity.ProcessGroup;

/**
 * Created by icetusk on 09.06.16.
 */
public class ProcessGroupDao extends BaseDaoImpl<ProcessGroup, Integer> {

    public ProcessGroupDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, ProcessGroup.class);
    }

}
