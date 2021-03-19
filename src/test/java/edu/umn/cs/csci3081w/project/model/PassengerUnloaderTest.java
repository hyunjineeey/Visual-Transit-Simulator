package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassengerUnloaderTest {

  /**
   * Setup deterministic operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = true;
  }

  /**
   * Test unloading of passengers.
   */
  @Test
  public void testUnloadPassengerNoPassengers() {
    List<Passenger> passengers = new ArrayList<>();
    Stop stop = new Stop(0, 44.972392, -93.243774);
    PassengerUnloader testPassUnloader = new PassengerUnloader();
    int passUnloaded = testPassUnloader.unloadPassengers(passengers, stop);
    assertEquals(0, passUnloaded);
  }

  /**
   * Test loading of passengers.
   */
  @Test
  public void testUnloadPassengers() {
    Passenger passenger1 = new Passenger(0, "Goldy");
    Passenger passenger2 = new Passenger(1, "Goldy");
    List<Passenger> passengers = new ArrayList<>();
    passengers.add(passenger1);
    passengers.add(passenger2);
    Stop stop = new Stop(0, 44.972392, -93.243774);
    PassengerUnloader testPassUnloader = new PassengerUnloader();
    int passUnloaded = testPassUnloader.unloadPassengers(passengers, stop);
  }
}
