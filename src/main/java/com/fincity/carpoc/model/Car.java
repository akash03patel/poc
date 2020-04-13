package com.fincity.carpoc.model;

import lombok.Builder;
import lombok.Data;

/**
 * The type Car.
 */
@Builder
@Data
public class Car {

  /**
   * Car Id use to uniquely identify the car.
   */
  private Long id;

  /**
   * Name of the car.
   */
  private String name;

  /**
   * Manufacturing company name.
   */
  private String manufactureName;

  /**
   * Manufacturing Year of the Car.
   */
  private String manufacturingYear;

  /**
   * Color of the car.
   */
  private String color;

  /**
   * Car Model.
   */
  private String model;
}
