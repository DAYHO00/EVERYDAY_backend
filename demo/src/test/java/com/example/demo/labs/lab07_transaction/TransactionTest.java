package com.example.demo.labs.lab07_transaction;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void rollback_demo() {
        tx_save_and_markRollback();
        long count = tx_count_in_new_tx();
        assertEquals(0L, count);
    }

    @Transactional
    void tx_save_and_markRollback() {
        em.persist(new TestMember("daeho"));
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    long tx_count_in_new_tx() {
        return em.createQuery("select count(m) from TestMember m", Long.class)
                .getSingleResult();
    }
}