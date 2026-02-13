package com.example.demo.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("""
        select distinct u
        from User u
        left join fetch u.posts
    """)
    List<User> findAllWithPosts();
}
