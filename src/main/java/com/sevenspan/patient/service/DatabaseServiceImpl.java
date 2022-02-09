package com.sevenspan.patient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.util.List;

public class DatabaseServiceImpl implements DatabaseService{

    @Autowired
    JdbcTemplate template;

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 1) {
            return Health.down().withDetail("Error Code", 500).build();
        }
        return Health.up().build();
    }

    public int check(){
        List<Object> results = template.query("select 1 from dual",
                new SingleColumnRowMapper<>());
        return results.size();
    }
}
