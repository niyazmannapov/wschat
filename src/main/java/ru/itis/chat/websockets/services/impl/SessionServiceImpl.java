package ru.itis.chat.websockets.services.impl;

import org.springframework.messaging.simp.SimpAttributes;
import org.springframework.messaging.simp.SimpAttributesContextHolder;
import org.springframework.stereotype.Service;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import ru.itis.chat.websockets.services.interfaces.SessionService;

import javax.management.Attribute;

@Service
class SessionServiceImpl implements SessionService {

    @Override
    public Object getAttribute(String name) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            Object attribute = attributes.getAttribute(name, RequestAttributes.SCOPE_SESSION);
            if (attribute != null) {
                return attribute;
            }
        }

        SimpAttributes simpAttributes = SimpAttributesContextHolder.getAttributes();
        if (simpAttributes != null) {
            return simpAttributes.getAttribute(name);
        }

        return null;
    }

    @Override
    public void setAttribute(String name, Object value) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            attributes.setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
        }

        SimpAttributes simpAttributes = SimpAttributesContextHolder.getAttributes();
        if (simpAttributes != null) {
            simpAttributes.setAttribute(name, value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            requestAttributes.removeAttribute(name, RequestAttributes.SCOPE_SESSION);
        }

        SimpAttributes simpAttributes = SimpAttributesContextHolder.getAttributes();
        if (simpAttributes != null) {
            simpAttributes.removeAttribute(name);
        }
    }
}
