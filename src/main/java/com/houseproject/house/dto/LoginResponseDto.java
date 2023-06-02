package com.houseproject.house.dto;

import com.houseproject.house.models.User;

public record LoginResponseDto(User user, String jwt) {
}
