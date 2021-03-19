package edu.umn.cs.csci3081w.project.model;

import javax.websocket.Session;

public abstract class BusDecorator {

  protected Bus bus;
  protected Session session;

  public BusDecorator(Bus bus) {
    this.bus = bus;
  }

  /**
   * Paints the bus.
   */
  public void paintBus() {
    if (!this.bus.getOutgoingRoute().isAtEnd()) {
      new MaroonPaint(this.bus);
    } else {
      new GoldPaint(this.bus);
    }

  }
}
