package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.ItemOwnerDifferenceException;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;
import ru.practicum.shareit.item.mapper.ItemMapperDto;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepositoryImpl itemRepository;

    private final UserRepository userRepository;

    private final ItemMapperDto itemMapperDto;

    @Override
    public ItemDto create(ItemDto itemDto, Long userId) {
        User user = userRepository.findById(userId);
        itemDto.setOwner(user);
        Item item = itemMapperDto.toItem(itemDto);
        itemMapperDto.toItemDto(itemDto, item);
        Item save = itemRepository.save(item);
        return itemMapperDto.toDto(save);
    }

    @Override
    public ItemDto update(ItemUpdateDto itemUpdateDto, Long userId, Long itemId) {
        if (!userRepository.existById(userId)) {
            throw new NotFoundException(String.format("Пользователя с id=%s не существует", userId));
        }
        User itemOwner = itemRepository.findById(itemId).getOwner();
        if (!userId.equals(itemOwner.getId())) {
            throw new ItemOwnerDifferenceException("Пользователи не совпадают по Id");
        }
        itemUpdateDto.setOwner(itemOwner);
        Item item = itemRepository.findById(itemId);
        itemMapperDto.toUpdateDto(itemUpdateDto, item);
        Item update = itemRepository.update(item);
        return itemMapperDto.toDto(update);
    }

    @Override
    public ItemDto findByIdDto(Long id) {
        Item item = itemRepository.findById(id);
        return itemMapperDto.toDto(item);
    }

    @Override
    public List<ItemDto> getAllItemDto(Long userId) {
        return itemRepository.getAllItemForUserId(userId).stream()
                .map(itemMapperDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> search(String text) {
        if(!isBlank(text)){
            String lowerCase = text.toLowerCase();
            List<Item> all = itemRepository.getAll();
            return all.stream()
                    .filter(item -> item.getName().toLowerCase().contains(lowerCase) || item.getDescription().toLowerCase().contains(lowerCase))
                    .filter(Item::isAvailable)
                    .map(itemMapperDto::toDto)
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
