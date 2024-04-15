package com.betrybe.agrix.ebytr.staff.advice;


import com.betrybe.agrix.ebytr.staff.exception.CropNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FarmNotFoundException;
import com.betrybe.agrix.ebytr.staff.exception.FertilizerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Controle de exceções.
 */
@ControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(FarmNotFoundException.class)
  public ResponseEntity<String> handleMuseumNotFoundException(FarmNotFoundException ex) {
    return ResponseEntity.status(404).body("Fazenda não encontrada!");
  }

  @ExceptionHandler(CropNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(CropNotFoundException ex) {
    return ResponseEntity.status(404).body("Plantação não encontrada!");
  }

  @ExceptionHandler(FertilizerNotFoundException.class)
  public ResponseEntity<String> handleCropNotFoundException(FertilizerNotFoundException ex) {
    return ResponseEntity.status(404).body("Fertilizante não encontrado!");
  }
}
