package com.service.dto;

import com.service.entity.Article;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String name;
    private Integer age;
    private List<Article> article;
}
