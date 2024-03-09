package ru.practicum.shareit.user;

import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto create(UserDto userDto);

    UserUpdateDto updateUser(UserDto userDto, Long userId);

    void deleteUser(Long userId);

    List<UserDto> getAllUserDto();

    UserDto getById(Long userId);
}
