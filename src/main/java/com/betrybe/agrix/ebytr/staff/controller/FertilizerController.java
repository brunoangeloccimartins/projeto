package com.betrybe.agrix.ebytr.staff.controller;

import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.FertilizerService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Camada controller fertilizers.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<FertilizerDto> createFertilizer(@RequestBody Fertilizer fertilizer) {
    Fertilizer createdFertilizer = fertilizerService.create(fertilizer);
    return ResponseEntity.status(201).body(FertilizerDto.fromEntity(createdFertilizer));
  }

  @GetMapping
  public ResponseEntity<List<FertilizerDto>> findAll() {
    List<Fertilizer> fertilizers = fertilizerService.findAll();
    return ResponseEntity.ok(fertilizers.stream().map(FertilizerDto::fromEntity).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<FertilizerDto> findById(@PathVariable Long id)
      throws FertilizerNotFoundException {
    Fertilizer fertilizer = fertilizerService.findById(id);
    return ResponseEntity.ok(FertilizerDto.fromEntity(fertilizer));
  }
}
