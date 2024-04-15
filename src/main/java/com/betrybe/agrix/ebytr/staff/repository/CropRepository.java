package com.betrybe.agrix.ebytr.staff.repository;


import com.betrybe.agrix.ebytr.staff.entity.Crop;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ORM para criação de tabela crops.
 */
@Repository

public interface CropRepository extends JpaRepository<Crop, Long> {

  List<Crop> findByFarmId(Long farmId);

  List<Crop> findByHarvestDateBetween(LocalDate firstDate, LocalDate secondDate);
}
