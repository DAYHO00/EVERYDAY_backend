package com.example.demo.labs.lab04_Exception;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Lab04ExceptionService { // 파일명이 Exception.java라서 클래스명도 일단 맞춰둠 (나중에 이름 정리해도 됨)
    private final JdbcTemplate jdbc;

    public Lab04ExceptionService(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // 초기화: 테스트에서 테이블 만들고 시작하려고 함
    public void initTable() {
        jdbc.execute("DROP TABLE IF EXISTS lab_tx");
        jdbc.execute("CREATE TABLE lab_tx (id BIGINT PRIMARY KEY, name VARCHAR(50))");
        jdbc.update("INSERT INTO lab_tx (id, name) VALUES (?, ?)", 1L, "init");
    }

    public String readName() {
        return jdbc.queryForObject("SELECT name FROM lab_tx WHERE id = 1", String.class);
    }

    // 1) Unchecked 예외(RuntimeException) -> 기본적으로 롤백됨
    @Transactional
    public void uncheckedRollback() {
        jdbc.update("UPDATE lab_tx SET name = ? WHERE id = 1", "changed_unchecked");
        throw new RuntimeException("unchecked 터짐");
    }

    // 2) Checked 예외(Exception) -> 기본은 롤백 안 될 수 있음(커밋될 수 있음)
    @Transactional
    public void checkedNoRollback() throws Exception {
        jdbc.update("UPDATE lab_tx SET name = ? WHERE id = 1", "changed_checked");
        throw new Exception("checked 터짐");
    }
}
