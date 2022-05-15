package com.technokratos.aiocars.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {

    USER("Пользователь"),
    ADMIN("Администратор");

    private final String description;
}
