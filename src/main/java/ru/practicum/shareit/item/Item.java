package ru.practicum.shareit.item;

import lombok.*;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class Item {
    private Long id;

    private String name;

    private String description;

    private boolean available;

    private User owner;

    private ItemRequest request;
}
