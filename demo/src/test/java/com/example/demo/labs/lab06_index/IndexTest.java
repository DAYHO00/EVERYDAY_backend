package com.example.demo.labs.lab06_index;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class IndexTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void explain_with_index() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS member_idx");

        jdbcTemplate.execute("""
            CREATE TABLE member_idx(
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50) NOT NULL,
                age INT NOT NULL
            )
        """);

        for (int i = 0; i < 200; i++) {
            jdbcTemplate.update(
                "INSERT INTO member_idx(name, age) VALUES(?, ?)",
                "user" + (i % 100),
                i % 50
            );
        }

        jdbcTemplate.execute("CREATE INDEX idx_name ON member_idx(name)");

        jdbcTemplate.query(
            "EXPLAIN SELECT * FROM member_idx WHERE name = 'user10'",
            rs -> {
                System.out.println("---- EXPLAIN RESULT ----");
                int colCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= colCount; i++) {
                    System.out.println(rs.getMetaData().getColumnName(i) + " = " + rs.getString(i));
                }
                System.out.println("------------------------");
            }
        );
    }
}
