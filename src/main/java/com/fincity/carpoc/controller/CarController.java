package com.fincity.carpoc.controller;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.fincity.carpoc.model.Car;
import com.fincity.carpoc.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

  @Autowired
  private ICarService carService;

  @GetMapping
  public ResponseEntity<List<Car>> getAllCars(final HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
  }

  @GetMapping("/search")
  public ResponseEntity<List<Car>> search(final HttpServletRequest httpServletRequest, @RequestParam() String searchText) {
    return new ResponseEntity<>(carService.serach(searchText), HttpStatus.OK);
  }

  @GetMapping("/{carId}")
  public ResponseEntity<Car> getCarById(@PathVariable("carId") final long carId,
                                        final HttpServletRequest httpServletRequest) {
    return new ResponseEntity<>(carService.getCar(carId), HttpStatus.OK);
  }

  @DeleteMapping("/{carId}")
  public ResponseEntity deleteCar(@PathVariable("carId") final long carId,
                                  final HttpServletRequest httpServletRequest) {
    carService.deleteCar(carId);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity addCar(@RequestBody final Car car,
                               final HttpServletRequest httpServletRequest) {
    carService.saveCar(car);
    return new ResponseEntity(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity updateCar(@RequestBody final Car car,
                                  final HttpServletRequest httpServletRequest) {
    carService.updateCar(car);
    return new ResponseEntity(HttpStatus.OK);
  }
}
