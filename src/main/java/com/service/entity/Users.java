package com.service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Article> article;

    public Users(String name, Integer age, List<Article> article) {
        this.name = name;
        this.age = age;
        this.article = article;
    }
}
