package com.jdbc.springbootjdbc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdbc.springbootjdbc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import  java.util.Map;

/**
 * @author loiter
 * @date 2020/10/24 23:53
 * @description
 */
@Service
@EnableTransactionManagement
public class DemoServiceImpl implements DemoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)public boolean save(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> map = objectMapper.readValue(json, Map.class);
            return  jdbcTemplate.execute("insert into t_user (name, age) values (?, ?)", new PreparedStatementCallback<Integer>() {
                @Override
                public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
                    ps.setString(1, (String)map.get("name"));
                    ps.setInt(2, (Integer)map.get("age"));

                    return ps.executeUpdate();
                }
            }) > 0 ;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
