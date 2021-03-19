package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomPassengerGeneratorTestFalse {

  /**
   * Setup deterministic operations before each test runs.
   */
  @BeforeEach
  public void setUp() {
    PassengerFactory.DETERMINISTIC = true;
    PassengerFactory.DETERMINISTIC_NAMES_COUNT = 0;
    PassengerFactory.DETERMINISTIC_DESTINATION_COUNT = 0;
    RandomPassengerGenerator.DETERMINISTIC = false;
  }

  /**
   * Test passenger generation.
   */
  @Test
  public void testGeneratePassengersTestWithProbsAndStopsTrue() {
    List<Double> probs = new ArrayList<>();
    probs.add(.5);
    probs.add(.4);
    probs.add(.3);
    List<Stop> stops = new ArrayList<>();
    Stop stop = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    stops.add(stop);
    stops.add(stop2);
    stops.add(stop3);
    RandomPassengerGenerator pasGen = new RandomPassengerGenerator(probs, stops);
    int passAdded = pasGen.generatePassengers();
  }

  /**
   * Test passenger generation.
   */
  @Test
  public void testGeneratePassengersTestWithProbsAndStops() {
    List<Double> probs = new ArrayList<>();
    probs.add(.5);
    probs.add(.4);
    probs.add(.3);
    List<Stop> stops = new ArrayList<>();
    Stop stop = new Stop(0, 44.972392, -93.243774);
    Stop stop2 = new Stop(1, 44.973580, -93.235071);
    Stop stop3 = new Stop(2, 44.975392, -93.226632);
    Stop stop4 = new Stop(3, 44.9752, -93.22632);
    stops.add(stop);
    stops.add(stop2);
    stops.add(stop3);
    stops.add(stop4);
    RandomPassengerGenerator pasGen = new RandomPassengerGenerator(probs, stops);
    int passAdded = pasGen.generatePassengers();
    assertEquals(4, pasGen.getStops().size());
  }
}
