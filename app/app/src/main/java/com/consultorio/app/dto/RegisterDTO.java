package com.consultorio.app.dto;


import com.consultorio.app.entity.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
