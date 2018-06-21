package org.orisonchan.sqlexporter.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Repository("mssqlDAO")
public class MssqlDAO {

  @Resource
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> query(String sql) {
    return  jdbcTemplate.queryForList(sql);
  }


}
