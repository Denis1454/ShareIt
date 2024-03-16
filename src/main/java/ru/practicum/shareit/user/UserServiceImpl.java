package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.UserDuplicateEmailException;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.dto.UserUpdateDto;
import ru.practicum.shareit.user.mapper.UserMapperDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;
import static ru.practicum.shareit.user.UserMapper.toUser;
import static ru.practicum.shareit.user.UserMapper.toUserDto;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;

    private final UserMapperDto userMapperDto;

    @Override
    public UserDto create(UserDto userDto) {
        checkDuplicatesEmail(userDto, userDto.getId());
        User save = userRepository.save(toUser(userDto));
        return toUserDto(save);
    }

    @Override
    public UserUpdateDto updateUser(UserDto userDto, Long userId) {
        User user = Optional.of(userRepository.findById(userId))
                .orElseThrow(() -> new NotFoundException("Пользователя с таким Id не существует"));
        checkDuplicatesEmail(userDto, userId);
        userMapperDto.updateUserDto(userDto, user);
        User updated = userRepository.updateUser(user);

        return userMapperDto.toDto(updated);
    }

    @Override
    public void deleteUser(Long userId) {
        if (userRepository.existById(userId)) {
            userRepository.deleteUser(userId);
        } else {
            throw new NotFoundException("Пользователя с таким Id не существует");
        }
    }

    @Override
    public List<UserDto> getAllUserDto() {
        return userRepository.getAllUser().stream()
                .map(UserMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long userId) {
        return toUserDto(userRepository.findById(userId));

    }

    private void checkDuplicatesEmail(UserDto userDto, Long userId) {
        List<User> emailExist = userRepository.getAllUser().stream()
                .filter(user -> user.getEmail().equals(userDto.getEmail()))
                .collect(Collectors.toList());
        if (!isEmpty(emailExist)) {
            User user = emailExist.get(0);
            if (userDto.getEmail().equals(user.getEmail()) && user.getId().equals(userId)) {
                return;
            }
            throw new UserDuplicateEmailException("Такой email уже зарегистрирован");
        }
    }
}