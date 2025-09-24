package paysecure.common.db.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import paysecure.common.db.mysql.model.dashboard.dashboardView.*;


import org.springframework.jdbc.core.RowMapper;

public class UserDashboardMapper implements RowMapper<UserDashboardStructure>{

    @Override
    public UserDashboardStructure mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserDashboardStructure info = new UserDashboardStructure();

        info.setInsights(rs.getString("insights"));
        info.setUserName(rs.getString("user_name"));

        return info;
    }
    
}