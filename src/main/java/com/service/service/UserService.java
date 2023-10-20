package com.service.service;

import com.service.converter.UsersToUserDto;
import com.service.dto.UserDto;
import com.service.dto.UserSaveRequest;
import com.service.entity.ColorArticle;
import com.service.entity.Users;
import com.service.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final int LIMIT = 5;
    private final UserRepository userRepository;
    private final UsersToUserDto usersToUserDto;

    public UserService(UserRepository userRepository, UsersToUserDto usersToUserDto) {
        this.userRepository = userRepository;
        this.usersToUserDto = usersToUserDto;
    }

    public List<UserDto> getUserByAge(Integer age) {
        UserDto userDto;
        List<Users> byAgeAfter = userRepository.findAllByAgeAfter(age);
        List<UserDto> result = new ArrayList<>();
        for (Users user : byAgeAfter) {
            userDto = usersToUserDto.convert(user);
            result.add(userDto);
        }
        return result;
    }

    public List<UserDto> getUserByColorArticle(ColorArticle color) {
        List<Users> byColor = userRepository.findAllByColor(color);
        UserDto userDto;
        List<UserDto> result = new ArrayList<>();
        for (Users user : byColor) {
            userDto = usersToUserDto.convert(user);
            result.add(userDto);
        }
        return result;
    }

    public List<String> getUserByCount(int count) {
        UserDto userDto;
        List<String> result = new ArrayList<>();
        int numberPage = 0;
        boolean isNotLast = true;
        while (isNotLast) {
            Page<Users> users = userRepository.findAll(PageRequest.of(numberPage, LIMIT));
            if (users.getContent().size() < LIMIT) {
                isNotLast = false;
            }
            for (Users user : users) {
                if (user.getArticle().size() > count) {
                    userDto = usersToUserDto.convert(user);
                    result.add(userDto.getName());
                }
            }
            numberPage++;
        }
        return result;
    }

    public void saveUser(UserSaveRequest userSaveRequest) {
        userRepository.save(new Users(userSaveRequest.getName(), userSaveRequest.getAge(), userSaveRequest.getArticles()));
    }

}
