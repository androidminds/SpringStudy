package cn.androidminds.springstudy.dao;

import cn.androidminds.springstudy.domain.LoginLog;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<LoginLog, Long> {
}
