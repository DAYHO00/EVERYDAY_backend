package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save_member_test() {
        System.out.println("=== TEST RUNNING ===");
        memberRepository.save(new Member("alic1e"));
        memberRepository.save(new Member("bob"));
        System.out.println(memberRepository.findAll()); 
        System.out.println("=== DONE ===");
    }
}
