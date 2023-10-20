package com.service.converter;

import com.service.dto.UserDto;
import com.service.entity.Users;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsersToUserDto implements Converter<Users, UserDto> {
    @Override
    public UserDto convert(Users user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAge(user.getAge());
        userDto.setArticle(user.getArticle());
        userDto.setName(user.getName());
        return userDto;
    }
}
