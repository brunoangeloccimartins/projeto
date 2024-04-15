package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.dto.FarmDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Farm;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada controller de farms.
 */
@RestController
@RequestMapping("/farms")
public class FarmController {


  private final FarmService farmService;
  private final CropService cropService;

  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  @PostMapping
  public ResponseEntity<FarmDto> createFarm(@RequestBody Farm farm) {
    Farm createdfarm = farmService.createFarm(farm);
    return ResponseEntity.status(201).body(FarmDto.fromEntity(createdfarm));
  }

  @GetMapping
  public ResponseEntity<List<FarmDto>> findAll() {
    List<Farm> farms = farmService.findAll();
    return ResponseEntity.ok(farms.stream().map(FarmDto::fromEntity).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> findById(@PathVariable Long id) throws FarmNotFoundException {
    Farm farm = farmService.findById(id);
    return ResponseEntity.ok(FarmDto.fromEntity(farm));
  }

  /**
   * Rota post para criar crops.
   *
   * @param farmId recebe farmId.
   * @param crop   recebe crop.
   * @return retorna crop criada com dto.
   * @throws FarmNotFoundException lança erro caso não encontre farm.
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(@PathVariable Long farmId, @RequestBody Crop crop)
      throws FarmNotFoundException {
    Farm farm = farmService.findById(farmId);
    crop.setFarm(farm);
    Crop createdCrop = cropService.createCrop(crop);
    return ResponseEntity.status(201).body(CropDto.fromEntity(createdCrop));
  }

  /**
   * Rota para pegar crops de uma fazenda by id.
   *
   * @param farmId recebe farmid.
   * @return retorna lista de crops dessa fazenda.
   * @throws FarmNotFoundException lança erro caso não encontre farm.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<List<CropDto>> findByFarmId(@PathVariable Long farmId)
      throws FarmNotFoundException {
    farmService.findById(farmId);
    List<Crop> crops = cropService.findByFarmId(farmId);
    return ResponseEntity.ok(crops.stream().map(CropDto::fromEntity).toList());
  }

}
