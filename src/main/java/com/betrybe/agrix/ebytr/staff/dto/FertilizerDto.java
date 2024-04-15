package com.betrybe.agrix.ebytr.staff.dto;

import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;

/**
 * Dto para fertilizantes.
 *
 * @param id          recebe id.
 * @param name        recebe name.
 * @param brand       recebe brand.
 * @param composition recebe composition.
 */
public record FertilizerDto(Long id, String name, String brand, String composition) {

  /**
   * Transforma fertilizer em fertilizerDto.
   *
   * @param fertilizer recebe fertilizer.
   * @return fertilizerDto.
   */
  public static FertilizerDto fromEntity(Fertilizer fertilizer) {
    return new FertilizerDto(
        fertilizer.getId(),
        fertilizer.getName(),
        fertilizer.getBrand(),
        fertilizer.getComposition()
    );
  }
}
