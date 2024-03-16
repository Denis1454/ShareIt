package ru.practicum.shareit.item;

import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;

import java.util.List;

public interface ItemService {
    ItemDto create(ItemDto itemDto, Long userId);

    ItemDto update(ItemUpdateDto itemUpdateDto, Long userId, Long itemId);

    ItemDto findByIdDto(Long id);

    List<ItemDto> getAllItemDto(Long userId);

    List<ItemDto> search(String text);
}
