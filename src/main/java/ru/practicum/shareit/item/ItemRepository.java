package ru.practicum.shareit.item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    Item update(Item item);

    Item findById(Long id);

    List<Item> getAllItemForUserId(Long userId);

    List<Item> getAll();
}
