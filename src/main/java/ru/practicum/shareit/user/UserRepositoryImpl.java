package ru.practicum.shareit.user;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final HashMap<Long,User> users = new HashMap<>();

    private static Long count = 1L;

    @Override
    public User save(User user) {
        user.setId(count);
        count++;
        users.put(user.getId(),user);
        return users.get(user.getId());
    }

    @Override
    public User findById(Long userId) {
        if(users.containsKey(userId)) {
            return users.get(userId);
        }
        throw new NotFoundException("Пользователя с таким Id не существует");
    }

    @Override
    public User updateUser(User user) {
        users.replace(user.getId(),user);
        return users.get(user.getId());
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void deleteUser(Long userId) {
        users.remove(userId);
    }

    @Override
    public boolean existById(Long userId) {
        return users.containsKey(userId);
    }
}
