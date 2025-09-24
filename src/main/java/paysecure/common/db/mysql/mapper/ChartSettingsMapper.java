package paysecure.common.db.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import paysecure.common.db.mysql.model.dashboard.dashboardView.*;


import org.springframework.jdbc.core.RowMapper;



public class ChartSettingsMapper implements RowMapper<ChartSettings> {

    @Override
    public ChartSettings mapRow(ResultSet rs, int rowNum) throws SQLException {
        ChartSettings info = new ChartSettings();

        info.setFrequency(rs.getString("frequency"));
        info.setGraphType(rs.getString("graph_type"));
        info.setInfoType(rs.getString("info_type"));
        info.setGroupByField(rs.getString("group_by_field"));
        info.setUserName(rs.getString("user_name"));
        info.setId(rs.getLong("id"));

        return info;
    }
    
}
