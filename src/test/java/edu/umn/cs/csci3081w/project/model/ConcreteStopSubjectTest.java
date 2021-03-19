package edu.umn.cs.csci3081w.project.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ConcreteStopSubjectTest {

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
   * Test constructor with parameters.
   */
  @Test
  public void testConstructorWithParameters() {
    ConcreteStopSubject concreteStopSubject = new ConcreteStopSubject(null);
    assertEquals(null, concreteStopSubject.getSession());
    assertEquals(0, concreteStopSubject.stopObservers.size());
  }

  /**
   * Test registering of stop observer.
   */
  @Test
  public void testRegisterStopObserver() {
    ConcreteStopSubject concreteStopSubject = new ConcreteStopSubject(null);
    concreteStopSubject.registerStopObserver(TestUtils.createStop());
    assertEquals(1, concreteStopSubject.stopObservers.size());
  }
}
