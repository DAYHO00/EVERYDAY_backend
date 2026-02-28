package com.example.demo.labs.lab09_log;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LogTest {

    private static final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    void print_vs_log() {
        System.out.println("println: 회원가입 시작");

        log.info("log.info: 회원가입 시작");
        log.warn("log.warn: 뭔가 이상함");
        log.error("log.error: 에러 발생");
    }
}