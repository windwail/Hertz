package checkmobile.de.hertz.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import checkmobile.de.hertz.entity.InfleetStart;

/**
 * Created by icetusk on 15.06.16.
 */
public class ImageDao extends BaseDaoImpl<InfleetStart, Integer> {

    public ImageDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, InfleetStart.class);
    }
}