package ru.practicum.shareit.item;

import org.springframework.stereotype.Repository;
import ru.practicum.shareit.exception.NotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ItemRepositoryImpl implements ItemRepository {
    private final HashMap<Long, Item> items = new HashMap<>();

    private static Long count = 1L;

    @Override
    public Item save(Item item) {
        item.setId(count);
        count++;
        items.put(item.getId(), item);
        return items.get(item.getId());
    }

    @Override
    public Item update(Item item) {
        items.replace(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(Long id) {
        Item item = items.get(id);
        if (item != null) {
            return item;
        }
        throw new NotFoundException("Предмета с таким Id не существует");
    }

    @Override
    public List<Item> getAllItemForUserId(Long userId) {
        return items.values().stream()
                .filter(item -> item.getOwner().getId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAll() {
        return new ArrayList<>(items.values());
    }
}
