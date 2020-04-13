package com.fincity.carpoc.dao.impl;

import java.util.List;
import com.fincity.carpoc.dao.ICarDao;
import com.fincity.carpoc.exception.CarNotFoundException;
import com.fincity.carpoc.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImpl implements ICarDao {

  /**
   * LOGGER.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(CarDaoImpl.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<Car> getAllCars() {
    LOGGER.info("Returning All the Cars");
    return jdbcTemplate.query("select * from cars", (resultSet, i) ->
        Car.builder()
            .id(resultSet.getLong("id"))
            .name(resultSet.getString("name"))
            .manufactureName(resultSet.getString("manufacture_name"))
            .manufacturingYear(resultSet.getString("manufacturing_year"))
            .color(resultSet.getString("color"))
            .model(resultSet.getString("model"))
            .build()
    );
  }

  @Override
  public Car getCar(Long carId) {
    LOGGER.info("Returning Car for carId: {}", carId);
    Car car = null;
    try {
      car = jdbcTemplate.queryForObject("select * from cars where id = ?",
          new Object[] {carId}, (resultSet, i) -> Car.builder()
              .id(resultSet.getLong("id"))
              .name(resultSet.getString("name"))
              .manufactureName(resultSet.getString("manufacture_name"))
              .manufacturingYear(resultSet.getString("manufacturing_year"))
              .color(resultSet.getString("color"))
              .model(resultSet.getString("model"))
              .build());
    } catch (Exception ex) {
      LOGGER.error("Exception while fetching object for card id: {}", carId);
      throw new CarNotFoundException("Car Not Found");
    }
    return car;
  }

  @Override
  public void saveCar(Car car) {
    LOGGER.info("Saving Car : {}", car);
    jdbcTemplate.update("insert into cars(name, manufacture_name, model, manufacturing_year, color) values(?,?,?,?,?);",
        car.getName(), car.getManufactureName(), car.getModel(), car.getManufacturingYear(), car.getColor());
  }

  @Override
  public void updateCar(Car car) {
    LOGGER.info("Updating Car : {} for carId: {}", car, car.getId());
    jdbcTemplate.update("update cars set name=?,manufacture_name=?,model=?,manufacturing_year=?,color=? where id = ?;",
        car.getName(), car.getManufactureName(), car.getModel(), car.getManufacturingYear(), car.getColor(), car.getId());
  }

  @Override
  public void deleteCar(Long carId) {
    LOGGER.info("Deleting Car for carId: {}", carId);
    jdbcTemplate.update("delete from cars where id=?", carId);
  }

  @Override
  public List<Car> search(String searchText) {
    LOGGER.info("Searching Cars for Search Text: {} ", searchText);
    return jdbcTemplate.query("SELECT id, name, manufacture_name, model, manufacturing_year, color "
            + " FROM cars where name=? or manufacture_name=? or model=? or manufacturing_year=? or color=?;", (resultSet, i) ->
            Car.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .manufactureName(resultSet.getString("manufacture_name"))
                .manufacturingYear(resultSet.getString("manufacturing_year"))
                .color(resultSet.getString("color"))
                .model(resultSet.getString("model"))
                .build()
        , searchText, searchText, searchText, searchText, searchText);
  }
}
