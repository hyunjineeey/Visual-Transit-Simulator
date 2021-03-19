package edu.umn.cs.csci3081w.project.model;

import java.util.List;

public class BusDeploymentStrategyMorning implements BusDeploymentStrategy {
  private int count = 0;
  private boolean testing = false;

  /**
   * Returns busses in the following order: small, regular, small,
   * regular, etc. (the sequence keeps repeating).
   *
   * @param name     parameter for the name of the bus
   * @param outbound parameter for outbound route
   * @param inbound  parameter for inbound route
   * @param speed    parameter for bus speed
   * @return the next bus according to the strategy
   */
  public Bus getNextBus(String name, Route outbound, Route inbound, double speed) {
    if (!testing) {
      this.count = 3;
    }
    int busType = count % 2;
    count++;
    if (busType == 0) {
      return new SmallBus(name, outbound, inbound, speed);
    } else {
      return new RegularBus(name, outbound, inbound, speed);
    }
  }

  public void setTesting(boolean testing) {
    this.testing = testing;
  }

}
