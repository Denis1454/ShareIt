package ru.practicum.shareit.exception;

public class ItemOwnerDifferenceException extends RuntimeException {
    public ItemOwnerDifferenceException(String message) {
        super(message);
    }
}
