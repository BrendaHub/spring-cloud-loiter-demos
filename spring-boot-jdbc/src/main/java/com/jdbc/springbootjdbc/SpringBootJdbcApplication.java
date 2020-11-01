package com.jdbc.springbootjdbc;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class SpringBootJdbcApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJdbcApplication.class, args);
    }

//    @Autowired
//    private HikariDataSource hikariDataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BasicDataSource basicDataSource;

    @Override
    public void run(String... args) throws Exception {
        AtomicLong totalConnectionNumber = new AtomicLong();

        MetricRegistry metricRegistry = new MetricRegistry();
        System.out.println(basicDataSource);
//        System.out.println(hikariDataSource);
//        hikariDataSource.setMetricRegistry(metricRegistry);
        int count = 0;//hikariDataSource.getHikariPoolMXBean().getTotalConnections();
        count = basicDataSource.getMinIdle();
        totalConnectionNumber.set(count);

        metricRegistry.register("totalConnectionNumber", new Gauge<Long>() {
            @Override
            public Long getValue() {
                return totalConnectionNumber.longValue();
            }
        });

        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).build();
        consoleReporter.start(10, TimeUnit.SECONDS);
    }

}
