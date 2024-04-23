package com.koreait.kod.controller.util;

import java.nio.file.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {

    private static final long serialVersionUID = 1L;

    private String role;

    public CustomAccessDeniedException(String message, String role) {
        super(message);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}