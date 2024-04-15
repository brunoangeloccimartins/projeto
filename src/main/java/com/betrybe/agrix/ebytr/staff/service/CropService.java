package com.betrybe.agrix.ebytr.staff.service;


import com.betrybe.agrix.ebytr.staff.entity.Crop;
import com.betrybe.agrix.ebytr.staff.entity.Fertilizer;
import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.CropRepository;
import com.betrybe.agrix.ebytr.staff.repository.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service para Crop.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Crop createCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> findByFarmId(Long farmId) {
    return cropRepository.findByFarmId(farmId);
  }

  public List<Crop> findAll() {
    return cropRepository.findAll();
  }

  public Crop findById(Long id) throws CropNotFoundException {
    return cropRepository.findById(id)
        .orElseThrow(CropNotFoundException::new);
  }

  public List<Crop> findByHarvestDateBetween(LocalDate firstDate, LocalDate secondDate) {
    return cropRepository.findByHarvestDateBetween(firstDate, secondDate);
  }

  /**
   * Método para associar fertilizante com plantação.
   *
   * @param cropId recebe id da plantação.
   * @param fertilizerId recebe id do fertilizante.
   * @return retorna a plantação associada.
   * @throws CropNotFoundException caso não encontre a plantação.
   * @throws FertilizerNotFoundException caso não encontre o fertilizante.
   */

  public Crop associateCropFertilizer(Long cropId, Long fertilizerId)
      throws CropNotFoundException, FertilizerNotFoundException {
    Crop crop = cropRepository.findById(cropId).orElseThrow(CropNotFoundException::new);
    Fertilizer fertilizer = fertilizerRepository.findById(fertilizerId)
        .orElseThrow(FertilizerNotFoundException::new);

    crop.getFertilizers().add(fertilizer);

    return cropRepository.save(crop);
  }

  public List<Fertilizer> findAllFertilizerByCropId(Long id) throws CropNotFoundException {
    Crop crop = cropRepository.findById(id).orElseThrow(CropNotFoundException::new);
    return crop.getFertilizers();
  }
}
