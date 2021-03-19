package edu.umn.cs.csci3081w.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerFactoryTestFalse {

  /**
   * Setup deterministic operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = false;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Testing generation of passengers.
   */
  @Test
  public void testGenerate() {
    Passenger testPassenger = PassengerFactory.generate(0, 3);
  }

  /**
   * Testing name generation for passenger.
   */
  @Test
  public void testNameGeneration() {
    String testName = PassengerFactory.nameGeneration();
  }
}
