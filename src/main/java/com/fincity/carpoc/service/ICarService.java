package com.fincity.carpoc.service;

import java.util.List;
import com.fincity.carpoc.exception.CarServiceException;
import com.fincity.carpoc.model.Car;

public interface ICarService {

  /**
   * Gets all cars.
   *
   * @return the all cars
   */
  List<Car> getAllCars() throws CarServiceException;

  /**
   * Gets car.
   *
   * @param carId the car id
   * @return the car
   */
  Car getCar(final Long carId) throws CarServiceException;

  /**
   * Save car.
   *
   * @param car the car
   */
  void saveCar(final Car car) throws CarServiceException;

  /**
   * Update car.
   *
   * @param car the car
   */
  void updateCar(final Car car) throws CarServiceException;

  /**
   * Delete car.
   *
   * @param carId the car id
   */
  void deleteCar(final Long carId) throws CarServiceException;

  List<Car> serach(final String serachText);
}
