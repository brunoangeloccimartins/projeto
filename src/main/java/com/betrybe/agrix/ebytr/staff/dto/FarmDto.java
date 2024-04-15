package com.betrybe.agrix.ebytr.staff.dto;


import com.betrybe.agrix.ebytr.staff.entity.Farm;
import java.time.LocalDate;

/**
 * Dto para respostas.
 *
 * @param name        recebe name;
 * @param size        recebe size;
 */
public record FarmDto(Long id, String name, Double size) {

  /**
   * Método para transformar farm em farmDto.
   *
   * @param farm recebe farm;
   * @return farmDto;
   */
  public static FarmDto fromEntity(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }
}
