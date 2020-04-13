package com.fincity.carpoc.service.impl;

import java.util.List;
import com.fincity.carpoc.dao.ICarDao;
import com.fincity.carpoc.exception.CarNotFoundException;
import com.fincity.carpoc.exception.CarServiceException;
import com.fincity.carpoc.model.Car;
import com.fincity.carpoc.service.ICarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements ICarService {

  /**
   * LOGGER.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CarServiceImpl.class);

  @Autowired
  private ICarDao carDao;

  @Override
  public List<Car> getAllCars() throws CarServiceException {
    LOGGER.info("Getting All Cars");
    try {
      return carDao.getAllCars();
    } catch (Exception ex) {
      LOGGER.error("Error while fetching all cars: {}", ex.getMessage());
      throw new CarServiceException(ex.getMessage());
    }
  }

  @Override
  public Car getCar(final Long carId) throws CarServiceException, CarNotFoundException {
    LOGGER.info("Getting Car for card ID: {}", carId);
    try {
      return carDao.getCar(carId);
    } catch (CarNotFoundException ex) {
      LOGGER.error("Error while fetching car: and carId{} {}", ex.getMessage(), carId);
      throw ex;
    } catch (Exception ex) {
      LOGGER.error("Error while fetching car: and carId{} {}", ex.getMessage(), carId);
      throw new CarServiceException(ex.getMessage());
    }
  }

  @Override
  public void saveCar(final Car car) throws CarServiceException {
    LOGGER.info("Saving Car {}", car);
    try {
      carDao.saveCar(car);
    } catch (Exception ex) {
      LOGGER.error("Error while saving car: {}", ex.getMessage(), car);
      throw new CarServiceException(ex.getMessage());
    }
  }

  @Override
  public void updateCar(final Car car) throws CarServiceException {
    LOGGER.info("Updating Car for card ID: {}", car.getId());
    try {
      carDao.updateCar(car);
    } catch (Exception ex) {
      LOGGER.error("Error while updating car: and carId{} {}", ex.getMessage(), car.getId());
      throw new CarServiceException(ex.getMessage());
    }
  }

  @Override
  public void deleteCar(final Long carId) throws CarServiceException {
    LOGGER.info("Deleting Car for card ID: {}", carId);
    try {
      carDao.deleteCar(carId);
    } catch (Exception ex) {
      LOGGER.error("Error while deleting car: and carId{} {}", ex.getMessage(), carId);
      throw new CarServiceException(ex.getMessage());
    }
  }

  @Override
  public List<Car> serach(String serachText) {
    LOGGER.info("Searching Cars");
    try {
      return carDao.search(serachText);
    } catch (Exception ex) {
      LOGGER.error("Error while Searching all cars: {}", ex.getMessage());
      throw new CarServiceException(ex.getMessage());
    }
  }
}
