package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Dto para retorno de person.
 */

public record PersonDto(Long id, String username, Role role) {

  /**
   * Método para transformar Person em PersonDto.
   *
   * @param person recebendo person.
   * @return retornando personDto.
   */
  public static PersonDto fromEntity(Person person) {
    return new PersonDto(
        person.getId(),
        person.getUsername(),
        person.getRole()
    );
  }
}