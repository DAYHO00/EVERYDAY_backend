package com.example.demo.labs.lab02_persistency;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@SpringBootTest
public class Persistency {

    // EntityManager manages the persistence context
    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void dirty_checking_test() {

            // 1) Create + persist
            Member m = new Member("Before");
            em.persist(m);
            em.flush();
            em.clear();

            Long id = m.getId();

            // 2) Load entity (managed)
            System.out.println("1) Loading entity (SELECT expected)");
            Member managed = em.find(Member.class, id);

            // 3) Change only (no save call)
            System.out.println("2) Changing field value (no SQL yet)");
            managed.setName("After");

            // 4) Flush triggers UPDATE
            System.out.println("3) Flushing (UPDATE expected)");
            em.flush();

            System.out.println("4) Done");
        }


}
