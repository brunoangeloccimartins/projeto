package com.betrybe.agrix.ebytr.staff.dto;


import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;

/**
 * Dto para respostas.
 *
 * @param id          recebe id.
 * @param name        recebe name.
 * @param plantedArea recebe planted area.
 * @param farmId      recebe farmId.
 * @param plantedDate recebe dia que foi plantada.
 * @param harvestDate recebe dia que vai ser colhido.
 */
public record CropDto(Long id, String name, Double plantedArea, LocalDate plantedDate,
                      LocalDate harvestDate, Long farmId) {

  /**
   * Método para transformar crop em cropDto.
   *
   * @param crop recebe crop.
   * @return cropDtop.
   */
  public static CropDto fromEntity(Crop crop) {
    return new CropDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );
  }
}

