package edu.umn.cs.csci3081w.project.model;

public class MaroonPaint extends BusDecorator {
  public MaroonPaint(Bus bus) {
    super(bus);
  }

  public void paintBus() {
    this.bus.getBusData().setColor("maroon");
  }
}
