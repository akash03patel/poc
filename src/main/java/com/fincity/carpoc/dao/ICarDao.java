package com.fincity.carpoc.dao;

import java.util.List;
import com.fincity.carpoc.model.Car;

/**
 * The interface Car dao.
 */
public interface ICarDao {

  /**
   * Gets all cars.
   *
   * @return the all cars
   */
  List<Car> getAllCars();

  /**
   * Gets car.
   *
   * @param carId the car id
   * @return the car
   */
  Car getCar(final Long carId);

  /**
   * Save car.
   *
   * @param car the car
   */
  void saveCar(final Car car);

  /**
   * Update car.
   *
   * @param car the car
   */
  void updateCar(final Car car);

  /**
   * Delete car.
   *
   * @param carId the car id
   */
  void deleteCar(final Long carId);

  /**
   * Serach list.
   *
   * @param searchText the search text
   * @return the list
   */
  List<Car> search(final String searchText);
}
