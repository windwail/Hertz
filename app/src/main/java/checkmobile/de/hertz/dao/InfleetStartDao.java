package checkmobile.de.hertz.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

import checkmobile.de.hertz.entity.InfleetStart;

public class InfleetStartDao extends BaseDaoImpl<InfleetStart, Integer> {

    public InfleetStartDao(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, InfleetStart.class);
    }

    public List<InfleetStart> getGoalByName(String name)  throws SQLException{
        QueryBuilder<InfleetStart, Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq("driverName", name);
        PreparedQuery<InfleetStart> preparedQuery = queryBuilder.prepare();
        List<InfleetStart> goalList =query(preparedQuery);
        return goalList;
    }
}
