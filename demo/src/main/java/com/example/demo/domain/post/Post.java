package com.example.demo.domain.post;

import com.example.demo.domain.user.User;
import jakarta.persistence.*;


@Entity
@Table(name = "posts")
public class Post {
    
    @Id @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")

    private User user;

    protected Post() {} 
    public Post(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }  

    public User getUser() {
        return user;
    }


}
