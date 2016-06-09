package checkmobile.de.hertz.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import checkmobile.de.hertz.entity.Process;

/**
 * Created by icetusk on 09.06.16.
 */
public class ProcessDao extends BaseDaoImpl<Process, Integer> {

    public ProcessDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Process.class);
    }

}
