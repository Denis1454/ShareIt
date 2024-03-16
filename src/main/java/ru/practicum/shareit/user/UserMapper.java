package ru.practicum.shareit.user;


import ru.practicum.shareit.user.dto.UserDto;

import javax.validation.constraints.NotNull;

public class UserMapper {
    public static UserDto toUserDto(@NotNull User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(@NotNull UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }
}
