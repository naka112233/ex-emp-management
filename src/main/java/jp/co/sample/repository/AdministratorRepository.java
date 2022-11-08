package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

@Repository
public class AdministratorRepository {
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i)->{
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	@Autowired
	private NamedParameterJdbcTemplate templeate;
	
	public void insert(Administrator administrator) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		
		String insertSql = "INSERT INTO administrators(name,mail_address,password)" + "VALUES(:name,:mailAddress,:password)";
		templeate.update(insertSql, param);
	}	
	public Administrator findByMailAddressAndPassword(String mailAdress,String password) {
			String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mailAddress=:mailAddress and password=:password";
	
			
			SqlParameterSource param = new MapSqlParameterSource();

			try {
				return templeate.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		}catch(Exception e) {
			return null;
		}
	
		
		
	}

	
		
}
