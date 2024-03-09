package ru.practicum.shareit.user;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User findById(Long userId);

    User updateUser(User user);

    List<User> getAllUser();

   void deleteUser(Long userId);

   boolean existById(Long userId);
}
