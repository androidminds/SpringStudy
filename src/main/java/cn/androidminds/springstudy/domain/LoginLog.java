package cn.androidminds.springstudy.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Table(name="t_login_log")
@Entity
public class LoginLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int log_id;
    private int user_id;
    private Date login_date;
    private String login_ip;

    protected LoginLog() {}
    public LoginLog(int uId, Date date, String ip) {
        user_id = uId;
        login_date = date;
        login_ip = ip;
    }

    @Override
    public String toString() {
        return String.format(
                "Login Log[id=%d, user=%d, date='%s', ip='%s']",
                log_id, user_id, login_date.toString(), login_ip);
    }

    public int getLog_id(){return log_id;}
    public int getUser_id() {return user_id;}
    public void setUser_id(int id) {user_id = id;}
    public Date getLogin_date(){return login_date;}
    public void setLogin_date(Timestamp date){login_date = date;}
    public String getLogin_ip(){return login_ip;}
    public void setLogin_ip(String ip){login_ip = ip;}
}
