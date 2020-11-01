package com.jdbc.springbootjdbc.controller;

import com.jdbc.springbootjdbc.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author loiter
 * @date 2020/10/24 20:09
 * @description Demo Controller JDBC
 * @see {@link}
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DemoService demoService;

    @GetMapping("/get")
    public Map<String, Object> get(@RequestParam(value = "id", defaultValue = "1") int id) {
        String _sql = "SELECT id, name, age FROM t_user where id = " + id;
        String sql = "SELECT id, name, age FROM t_user where id = ?";

        return jdbcTemplate.query(_sql, new ResultSetExtractor<Map<String, Object>>() {

            @Override
            public Map<String, Object> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<String, Object> resultSet = new HashMap<>();
                if (rs.next()) {
                    resultSet.put("id", rs.getInt("id"));
                    resultSet.put("name", rs.getString("name"));
                    resultSet.put("age", rs.getInt("age"));
                }
                return resultSet;
            }
        });
    }

    ;

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> get(@RequestBody(required = true) String json) {
        return new HashMap<String, Object>() {
            {
                put("result", Boolean.valueOf(demoService.save(json)));
            }
        };
    }

    // 自定义一个通过的of方法
    private static <T> T[] of(T... array) {
        return array;
    }
}
