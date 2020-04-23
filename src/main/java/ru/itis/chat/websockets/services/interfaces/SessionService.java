package ru.itis.chat.websockets.services.interfaces;

import org.springframework.lang.Nullable;

public interface SessionService {

    @Nullable
    Object getAttribute(String name);

    void setAttribute(String name, Object value);

    void removeAttribute(String name);
}
