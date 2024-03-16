package ru.practicum.shareit.item;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.ItemUpdateDto;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping()
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody @Validated ItemDto itemDto) {
        return itemService.create(itemDto, userId);
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader("X-Sharer-User-Id") Long userId, @RequestBody @Valid ItemUpdateDto itemUpdateDto, @PathVariable Long itemId) {
        return itemService.update(itemUpdateDto, userId,itemId);
    }

    @GetMapping("/{itemId}")
    public ItemDto findById(@PathVariable Long itemId) {
        return itemService.findByIdDto(itemId);
    }

    @GetMapping()
    public List<ItemDto> getAllItemUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        return itemService.getAllItemDto(userId);
    }
    @GetMapping("/search")
    public List<ItemDto> search(@RequestParam("text") String text) {
        return itemService.search(text);
    }
}
