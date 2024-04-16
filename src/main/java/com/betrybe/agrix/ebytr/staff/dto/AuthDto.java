package com.betrybe.agrix.ebytr.staff.dto;

/**
 * Dto para autorização.
 *
 * @param username usuário.
 * @param password senha.
 */

public record AuthDto(String username, String password) {

}
