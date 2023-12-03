package me.ricky.guides.securityguides.config;



import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnectionTest(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void run(String... args) throws Exception {
        String sql = "SELECT 1";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);

        if (result != null && result == 1) {
            System.out.println("Database connection test: SUCCESS");
        } else {
            System.out.println("Database connection test: FAILURE");
        }
    }
}