package com.fincity.carpoc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health controller will check the application health.
 */
@RestController
@RequestMapping("health")
public class HealthController {

  /**
   * The Logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(HealthController.class);

  /**
   * Health status Check.
   *
   * @return Health string
   */
  @GetMapping
  public String healthStatus() {
    final StringBuilder tableResponsesB = new StringBuilder("<table border='1'>");
    tableResponsesB.append("<tr><td>HEALTH_STATUS</td> <td>OK</td></tr> </table>");
    return tableResponsesB.toString();
  }
}