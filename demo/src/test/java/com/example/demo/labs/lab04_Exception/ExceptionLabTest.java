package com.example.demo.labs.lab04_Exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExceptionLabTest {

   @Autowired
    private Lab04ExceptionService service;

    @BeforeEach
    void setUp() {
        service.initTable();
    }

    @Test
    void unchecked_exception_should_rollback() {
        assertEquals("init", service.readName());

        try {
            service.uncheckedRollback();
            fail("여기 오면 안 됨");
        } catch (RuntimeException e) {
            // expected
        }

        // ✅ 롤백되면 init 그대로
        assertEquals("init", service.readName());
    }

    @Test
    void checked_exception_may_commit_by_default() throws Exception {
        assertEquals("init", service.readName());

        try {
            service.checkedNoRollback();
            fail("여기 오면 안 됨");
        } catch (Exception e) {
            // expected
        }

        // ❓ 기본 설정에서는 checked는 롤백 안 하고 커밋될 수 있음
        // 그래서 여기 결과가 "changed_checked"로 나올 가능성이 큼
        System.out.println("[after checked] name = " + service.readName());
    }
}
