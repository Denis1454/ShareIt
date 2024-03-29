package ru.practicum.shareit.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class User {
    private Long id;

    private String name;

    private String email;
}
