package com.example.demo.labs.lab01_ddl_auto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;

@SpringBootTest
public class DdlAutoTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void ddl_auto_test() {
        memberRepository.save(new Member("Alice"));
        memberRepository.save(new Member("Bob"));

        System.out.println(memberRepository.findAll());
    }
}
