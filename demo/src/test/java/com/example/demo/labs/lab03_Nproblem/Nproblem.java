package com.example.demo.labs.lab03_Nproblem;

import com.example.demo.domain.post.Post;
import com.example.demo.domain.post.PostRepository;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class Nproblem {

    @Autowired jakarta.persistence.EntityManager em;
    @Autowired UserRepository userRepository;
    @Autowired PostRepository postRepository;

    @Test
    @Transactional
    void reproduce_N_plus_1() {
        postRepository.deleteAll();
        userRepository.deleteAll();
        em.flush();
        em.clear();
        // 1) 데이터 준비
        User u1 = userRepository.save(new User("u1"));
        User u2 = userRepository.save(new User("u2"));
        User u3 = userRepository.save(new User("u3"));

        postRepository.save(new Post("u1-post1", u1));
        postRepository.save(new Post("u1-post2", u1));
        postRepository.save(new Post("u2-post1", u2));
        postRepository.save(new Post("u3-post1", u3));
        postRepository.save(new Post("u3-post2", u3));
        postRepository.save(new Post("u3-post3", u3));


        em.flush();
        em.clear();
        // 2) 유저 조회 (여기서 SQL 1번)
        List<User> users = userRepository.findAll();

        // 3) posts 접근 (여기서 유저 수만큼 posts 조회가 나가면 N+1)
        for (User u : users) {
            System.out.println(u.getName() + " posts size=" + u.getPosts().size());
        }
    }
    @Test
    @Transactional
    void solve_with_fetch_join() {
    // 데이터 준비 (기존과 동일)

        postRepository.deleteAll();
        userRepository.deleteAll();
        em.flush();
        em.clear();
        User u1 = userRepository.save(new User("u1"));
        User u2 = userRepository.save(new User("u2"));
        User u3 = userRepository.save(new User("u3"));

        postRepository.save(new Post("u1-post1", u1));
        postRepository.save(new Post("u1-post2", u1));
        postRepository.save(new Post("u2-post1", u2));
        postRepository.save(new Post("u3-post1", u3));
        postRepository.save(new Post("u3-post2", u3));
        postRepository.save(new Post("u3-post3", u3));

        em.flush();
        em.clear();

        // ✅ 해결 버전 조회 (fetch join)
        List<User> users = userRepository.findAllWithPosts();

        // posts 접근해도 추가 쿼리 없어야 함
        for (User u : users) {
            System.out.println("[fetch join] " + u.getName() + " posts size=" + u.getPosts().size());
        }
    }
}
