package com.betrybe.agrix.ebytr.staff.controller;


import com.betrybe.agrix.ebytr.staff.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.dto.FertilizerDto;
import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller crop.
 */
@RestController
@RequestMapping("/crops")
public class CropController {

  private final CropService cropService;

  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  @GetMapping
  @PreAuthorize("hasAuthority('MANAGER') or hasAuthority('ADMIN')")
  public ResponseEntity<List<CropDto>> findAll() {
    List<Crop> crops = cropService.findAll();
    return ResponseEntity.ok(crops.stream().map(CropDto::fromEntity).toList());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CropDto> findById(@PathVariable Long id) throws CropNotFoundException {
    return ResponseEntity.ok(CropDto.fromEntity(cropService.findById(id)));
  }

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> findByDate(
      @RequestParam("start") @DateTimeFormat(iso = ISO.DATE)
      LocalDate startDate, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE) LocalDate endDate) {
    List<Crop> crops = cropService.findByHarvestDateBetween(startDate, endDate);
    return ResponseEntity.ok(crops.stream().map(CropDto::fromEntity).toList());
  }

  @PostMapping("/{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropFertilizer(@PathVariable Long cropId,
      @PathVariable Long fertilizerId) throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropService.associateCropFertilizer(cropId, fertilizerId);
    return ResponseEntity.status(201).body("Fertilizante e plantação associados com sucesso!");
  }

  @GetMapping("/{cropId}/fertilizers")
  public ResponseEntity<List<FertilizerDto>> findByCropId(@PathVariable Long cropId)
      throws CropNotFoundException {
    List<Fertilizer> fertilizers = cropService.findAllFertilizerByCropId(cropId);
    return ResponseEntity.ok(fertilizers.stream().map(FertilizerDto::fromEntity).toList());
  }
}
