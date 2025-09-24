package paysecure.common.db.mysql.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import paysecure.common.db.mysql.mapper.MerchantProfileMapper;
import paysecure.common.db.mysql.model.MerchantProfile;


@Repository
public class MerchantProileDao {

    private static final String SQL_FIND_PROFILE = "select * from merchant_profile where login_id = ?";

    private final JdbcTemplate jdbcTemplate;

    public MerchantProileDao(@Qualifier("primaryJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    public MerchantProfile getProfile(long login_id) {
		MerchantProfile MP = null;
		try {
			MP = jdbcTemplate.queryForObject(SQL_FIND_PROFILE, new Object[] { login_id }, new MerchantProfileMapper());
		} catch (Exception e) {
			MP = new MerchantProfile();
			MP.setLogin_id(login_id);
		}

		return MP;
	}
    
}
