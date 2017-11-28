package cn.androidminds.springstudy.dao;

import cn.androidminds.springstudy.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class UserDao {
	private JdbcTemplate jdbcTemplate;

	private  final static String MATCH_COUNT_SQL = " SELECT count(*) FROM t_user  " +
			" WHERE user_name =? and password=? ";

	private  final static String UPDATE_LOGIN_INFO_SQL = " UPDATE t_user SET " +
            " last_visit=?,last_ip=?,credits=?  WHERE user_id =?";

    private  final static String INSERT_USER_SQL = " INSERT into t_user(user_name,password) VALUES(?,?)";

	public int getMatchCount(String userName, String password) {

		return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
	}

	public User findUserByUserName(final String userName) {
		String sqlStr = " SELECT user_id,user_name,credits "
				+ " FROM t_user WHERE user_name =? ";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[] { userName },
				new RowCallbackHandler() {
					public void processRow(ResultSet rs) throws SQLException {
						user.setUserId(rs.getInt("user_id"));
						user.setUserName(userName);
						user.setCredits(rs.getInt("credits"));
					}
				});
		return user;
	}

	public void updateLoginInfo(User user) {
		jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[] { user.getLastVisit(),
				user.getLastIp(),user.getCredits(),user.getUserId()});
	}

	public boolean addUser(String userName, String password) {
        jdbcTemplate.update(INSERT_USER_SQL, new Object[] { userName, password}, new int[]{
            Types.VARCHAR, Types.VARCHAR});
        return true;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
