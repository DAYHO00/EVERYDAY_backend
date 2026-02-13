package com.example.demo.domain.user;

import com.example.demo.domain.post.Post;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy="user", fetch=FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    protected User() {}

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Post> getPosts() {
        return posts;
    }
    
}
