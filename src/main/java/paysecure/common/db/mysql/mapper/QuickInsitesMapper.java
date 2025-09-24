package paysecure.common.db.mysql.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
 
import paysecure.common.db.mysql.model.dashboard.dashboardView.*;


import org.springframework.jdbc.core.RowMapper;

public class QuickInsitesMapper implements RowMapper<QuickInsites>{

    @Override
    public QuickInsites mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuickInsites info = new QuickInsites();

        info.setNameId(rs.getString("name_id"));
        info.setName(rs.getString("name"));
        info.setCategory(rs.getString("category"));
        info.setType(rs.getString("type"));

        return info;
    }
    
}

