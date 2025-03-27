package com.global.jwt;

public record JwtResponse(String token, Long expiration) {
}
