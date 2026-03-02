package com.example.demo.labs.lab10_id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

@SpringBootTest
@Transactional
class IdStrategyTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    void identity_vs_sequence_two_experiments() {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        System.out.println("\n===== [실험 1] IDENTITY =====");
        IdentityMember im = new IdentityMember("identity");
        em.persist(im);
        System.out.println("IDENTITY - after persist, id = " + im.getId());
        System.out.println("IDENTITY - now flush()");
        em.flush();
        System.out.println("IDENTITY - after flush, id = " + im.getId());

        System.out.println("\n===== [실험 2] SEQUENCE =====");
        SequenceMember sm = new SequenceMember("sequence");
        em.persist(sm);
        System.out.println("SEQUENCE - after persist, id = " + sm.getId());
        System.out.println("SEQUENCE - now flush()");
        em.flush();
        System.out.println("SEQUENCE - after flush, id = " + sm.getId());

        em.getTransaction().rollback();
        em.close();
    }
}